#!/bin/bash

echo "🍎 Creando aplicación nativa para macOS..."

# Configuración
APP_NAME="SistemaTEC"
APP_VERSION="1.0"
MAIN_CLASS="com.tec.Login"  # Cambia esto por tu clase principal
MAIN_JAR="target/App-1.0-SNAPSHOT.jar"
ICON_PATH="src/main/resources/icon.icns"  # Opcional

# Limpiar builds anteriores
echo "🧹 Limpiando builds anteriores..."
rm -rf build/
mkdir -p build

# Compilar el proyecto
echo "🔨 Compilando proyecto..."
./mvnw clean package

# Verificar que el JAR existe
if [ ! -f "$MAIN_JAR" ]; then
    echo "❌ Error: No se encontró el JAR en $MAIN_JAR"
    exit 1
fi

# Crear la aplicación .app
echo "📦 Creando bundle .app..."

# Comando básico sin icono
JPACKAGE_CMD="jpackage \
  --input target \
  --name $APP_NAME \
  --main-jar App-1.0-SNAPSHOT.jar \
  --main-class $MAIN_CLASS \
  --type app-image \
  --dest build \
  --app-version $APP_VERSION \
  --vendor 'Tu Nombre' \
  --copyright 'Copyright 2025' \
  --module-path target/lib \
  --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.media,javafx.swing \
  --java-options '-Xmx512m' \
  --mac-package-identifier com.tec.app"

# Agregar icono si existe
if [ -f "$ICON_PATH" ]; then
    JPACKAGE_CMD="$JPACKAGE_CMD --icon $ICON_PATH"
fi

# Ejecutar jpackage
eval $JPACKAGE_CMD

if [ $? -eq 0 ]; then
    echo "✅ ¡Aplicación creada exitosamente!"
    echo "📂 Ubicación: build/$APP_NAME.app"
    echo ""
    echo "Para ejecutar:"
    echo "  open build/$APP_NAME.app"
    echo ""
    echo "Para crear un instalador DMG:"
    echo "  jpackage --type dmg --app-image build/$APP_NAME.app --dest build"
else
    echo "❌ Error al crear la aplicación"
    exit 1
fi

chmod +x create-mac-app.sh
ls


