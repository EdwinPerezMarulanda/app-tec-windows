#!/bin/bash

echo "🍎 Creando aplicación macOS..."

# Limpiar
echo "🧹 Limpiando..."
rm -rf build/
mkdir -p build
xattr -cr target/ 2>/dev/null || true

# Compilar
echo "🔨 Compilando..."
./mvnw clean package

if [ ! -f "target/App-1.0-SNAPSHOT.jar" ]; then
    echo "❌ Error: JAR no creado"
    exit 1
fi

# Crear .app
echo "📦 Creando .app..."
jpackage \
  --input target \
  --name SistemaTEC \
  --main-jar App-1.0-SNAPSHOT.jar \
  --main-class com.tec.app.Login \
  --type app-image \
  --dest build \
  --app-version 1.0 \
  --module-path target/lib \
  --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.media

# Verificar si se creó
if [ -d "build/SistemaTEC.app" ]; then
    echo "🔓 Removiendo firma..."
    codesign --remove-signature build/SistemaTEC.app 2>/dev/null || true
    xattr -cr build/SistemaTEC.app
    
    echo ""
    echo "✅ ¡Aplicación creada!"
    echo "📂 Ubicación: build/SistemaTEC.app"
    echo ""
    echo "Para ejecutar:"
    echo "  open build/SistemaTEC.app"
else
    echo "❌ Error: No se pudo crear la aplicación"
    exit 1
fi
