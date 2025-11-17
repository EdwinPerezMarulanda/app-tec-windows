#!/bin/bash

APP_NAME="SistemaTEC"
BUNDLE_DIR="$APP_NAME.app"

echo "🍎 Creando bundle .app..."

# Compilar
echo "🔨 Compilando proyecto..."
./mvnw clean package

if [ ! -f "target/App-1.0-SNAPSHOT.jar" ]; then
    echo "❌ Error: JAR no creado"
    exit 1
fi

# Crear estructura PRIMERO
echo "📁 Creando estructura de la app..."
rm -rf "$BUNDLE_DIR"
mkdir -p "$BUNDLE_DIR/Contents/MacOS"
mkdir -p "$BUNDLE_DIR/Contents/Resources"
mkdir -p "$BUNDLE_DIR/Contents/Java"

# Copiar JARs
echo "📦 Copiando librerías..."
cp target/*.jar "$BUNDLE_DIR/Contents/Java/"
cp -r target/lib "$BUNDLE_DIR/Contents/Java/"

# Copiar ícono DESPUÉS de crear la estructura
echo "🎨 Copiando ícono..."
if [ -f "app-icon.icns" ]; then
    cp app-icon.icns "$BUNDLE_DIR/Contents/Resources/"
    echo "   ✅ Ícono copiado desde raíz"
elif [ -f "src/main/resources/icons/icon.icns" ]; then
    cp src/main/resources/icons/icon.icns "$BUNDLE_DIR/Contents/Resources/app-icon.icns"
    echo "   ✅ Ícono copiado desde resources/icons"
else
    echo "   ⚠️  No se encontró ícono"
fi

# Crear launcher
echo "🔧 Creando launcher..."
cat > "$BUNDLE_DIR/Contents/MacOS/$APP_NAME" << 'LAUNCHER'
#!/bin/bash
cd "$(dirname "$0")/../Java"
java --module-path lib --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.media -cp "App-1.0-SNAPSHOT.jar:lib/*" com.tec.app.Login
LAUNCHER

chmod +x "$BUNDLE_DIR/Contents/MacOS/$APP_NAME"

# Crear Info.plist
echo "📝 Creando Info.plist..."
cat > "$BUNDLE_DIR/Contents/Info.plist" << 'PLIST'
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>CFBundleExecutable</key>
    <string>SistemaTEC</string>
    <key>CFBundleName</key>
    <string>Sistema TEC</string>
    <key>CFBundleIdentifier</key>
    <string>com.tec.app</string>
    <key>CFBundleVersion</key>
    <string>1.0</string>
    <key>CFBundlePackageType</key>
    <string>APPL</string>
    <key>CFBundleIconFile</key>
    <string>app-icon.icns</string>
    <key>CFBundleShortVersionString</key>
    <string>1.0</string>
    <key>LSMinimumSystemVersion</key>
    <string>10.13</string>
    <key>NSHighResolutionCapable</key>
    <true/>
</dict>
</plist>
PLIST

echo ""
echo "✅ ¡Bundle .app creado exitosamente!"
echo "📂 Ubicación: $BUNDLE_DIR"
echo ""
echo "Para ejecutar:"
echo "  open $BUNDLE_DIR"
