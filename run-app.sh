#!/bin/bash
cd "$(dirname "$0")"

java \
  --module-path target/lib \
  --add-modules javafx.controls,javafx.fxml,javafx.web,javafx.media \
  -cp "target/App-1.0-SNAPSHOT.jar:target/lib/*" \
  com.tec.app.Login
