#!/bin/bash

APP_NAME="SistemaTEC"
DMG_NAME="SistemaTEC-Installer"
VOLUME_NAME="Sistema TEC"

echo "📀 Creando instalador DMG..."

# Crear la app primero
./create-simple-app.sh

if [ ! -d "$APP_NAME.app" ]; then
    echo "❌ Error: No se encontró $APP_NAME.app"
    exit 1
fi

# Limpiar DMG anterior
rm -f "$DMG_NAME.dmg"

# Crear carpeta temporal para el contenido del DMG
TMP_DIR="dmg_temp"
rm -rf "$TMP_DIR"
mkdir -p "$TMP_DIR"

echo "📋 Preparando contenido del instalador..."

# Copiar la app a la carpeta temporal
cp -r "$APP_NAME.app" "$TMP_DIR/"

# Crear enlace simbólico a Aplicaciones
ln -s /Applications "$TMP_DIR/Applications"

# Opcional: Crear un README
cat > "$TMP_DIR/Instrucciones.txt" << 'README'
===========================================
     SISTEMA TEC - INSTALACIÓN
===========================================

Para instalar:
1. Arrastra "SistemaTEC.app" a la carpeta "Applications"
2. Abre la app desde el Launchpad o la carpeta Aplicaciones

Si aparece un mensaje de seguridad:
- Haz clic derecho en la app
- Selecciona "Abrir"
- Confirma "Abrir" en el diálogo

Nota: Esta app requiere Java 17 o superior.

© 2025 - Sistema TEC
===========================================
README

# Crear el DMG
echo "🔨 Generando imagen de disco..."
hdiutil create -volname "$VOLUME_NAME" \
    -srcfolder "$TMP_DIR" \
    -ov \
    -format UDZO \
    "$DMG_NAME.dmg"

# Limpiar
rm -rf "$TMP_DIR"

if [ -f "$DMG_NAME.dmg" ]; then
    SIZE=$(du -h "$DMG_NAME.dmg" | cut -f1)
    echo ""
    echo "✅ ¡Instalador DMG creado exitosamente!"
    echo "📂 Archivo: $DMG_NAME.dmg"
    echo "💾 Tamaño: $SIZE"
    echo ""
    echo "Para probarlo:"
    echo "  open $DMG_NAME.dmg"
    echo ""
    echo "Para distribuir:"
    echo "  - Comparte el archivo $DMG_NAME.dmg"
    echo "  - Los usuarios solo arrastran la app a Applications"
else
    echo "❌ Error al crear el DMG"
    exit 1
fi
