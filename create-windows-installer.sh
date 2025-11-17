#!/bin/bash

APP_NAME="SistemaTEC"
INSTALLER_NAME="SistemaTEC-Installer"
VERSION="1.0.0"

echo "🪟 Creando instalador para Windows..."

# Verificar que exista jpackage
if ! command -v jpackage &> /dev/null; then
    echo "❌ Error: jpackage no está instalado"
    echo "   Instala JDK 17 o superior que incluya jpackage"
    exit 1
fi

# Verificar que exista el JAR
if [ ! -f "target/$APP_NAME.jar" ]; then
    echo "❌ Error: No se encontró target/$APP_NAME.jar"
    echo "   Ejecuta primero: mvn clean package"
    exit 1
fi

echo "📋 Preparando recursos del instalador..."

# Crear directorio para recursos
RESOURCES_DIR="installer-resources/windows"
mkdir -p "$RESOURCES_DIR"

# Crear archivo de configuración de la app
cat > "$RESOURCES_DIR/app.properties" << 'CONFIG'
app.name=Sistema TEC
app.version=1.0.0
app.vendor=Sistema TEC
CONFIG

# Crear icono si no existe (placeholder)
if [ ! -f "$RESOURCES_DIR/app-icon.ico" ]; then
    echo "ℹ️  Nota: Coloca un archivo 'app-icon.ico' en $RESOURCES_DIR para personalizar el icono"
fi

echo "🔨 Generando instalador EXE con jpackage..."

# Crear instalador EXE
jpackage \
    --input target \
    --name "$APP_NAME" \
    --main-jar "$APP_NAME.jar" \
    --main-class com.sistemaTEC.Main \
    --type exe \
    --app-version "$VERSION" \
    --vendor "Sistema TEC" \
    --description "Sistema de gestión TEC" \
    --win-dir-chooser \
    --win-menu \
    --win-shortcut \
    --dest installer-output

# Verificar resultado
if [ -f "installer-output/$APP_NAME-$VERSION.exe" ]; then
    SIZE=$(du -h "installer-output/$APP_NAME-$VERSION.exe" | cut -f1)
    echo ""
    echo "✅ ¡Instalador Windows creado exitosamente!"
    echo "📂 Archivo: installer-output/$APP_NAME-$VERSION.exe"
    echo "💾 Tamaño: $SIZE"
    echo ""
    echo "Características del instalador:"
    echo "  ✓ Instalador .exe nativo de Windows"
    echo "  ✓ Incluye JRE embebido (no requiere Java instalado)"
    echo "  ✓ Permite elegir directorio de instalación"
    echo "  ✓ Crea acceso directo en el menú de inicio"
    echo "  ✓ Crea acceso directo en el escritorio"
    echo ""
    echo "Para distribuir:"
    echo "  - Comparte el archivo .exe"
    echo "  - Los usuarios ejecutan el instalador"
    echo "  - El instalador guía el proceso de instalación"
else
    echo "❌ Error al crear el instalador"
    exit 1
fi