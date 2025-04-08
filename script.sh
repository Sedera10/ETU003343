#!/bin/bash

# Variables
PROJECT_NAME="ETU003343"
TOMCAT_HOME="/home/sedera/Documents/S4/apache-tomcat-10.1.28"
BUILD_DIR="build"
SOURCE_DIR="src/main/java"
LIB_DIR="lib"
SOURCE_WEBAPP="src/main/webapp"
WAR_NAME="$PROJECT_NAME.war"
WEBAPPS_DIR="$TOMCAT_HOME/webapps"

# Nettoyer et recréer le dossier build
if [ -d "$BUILD_DIR" ]; then
    echo "Effacement du dossier build existant..."
    rm -rf "$BUILD_DIR"
fi
mkdir "$BUILD_DIR"
echo "Dossier build créé."

# Copier les fichiers HTML et WEB-INF dans build
echo "Copie des fichiers HTML et configuration..."
cp -r "$SOURCE_WEBAPP"/* "$BUILD_DIR/"
echo "Fichiers copiés avec succès."

# Chercher toutes les classes Java dans src/main/java
find "$SOURCE_DIR" -name "*.java" > source.txt
echo "Fichier source.txt créé avec les classes Java."

# Compiler les fichiers Java
mkdir -p "$BUILD_DIR/WEB-INF/classes"
javac -d "$BUILD_DIR/WEB-INF/classes" -cp "$LIB_DIR/jakarta.servlet-api-6.0.0.jar" @source.txt

if [ $? -ne 0 ]; then
    echo "Erreur lors de la compilation des fichiers Java."
    rm source.txt
    exit 1
fi
echo "Fichiers Java compilés avec succès."

# Supprimer le fichier source.txt
rm source.txt
echo "Fichier source.txt supprimé."

# Création du fichier WAR
WAR_TEMP_DIR="temp_war"
mkdir -p "$WAR_TEMP_DIR/WEB-INF"
cp -r "$BUILD_DIR"/* "$WAR_TEMP_DIR/"

# Inclure les fichiers JAR dans lib
mkdir -p "$WAR_TEMP_DIR/WEB-INF/lib"
cp "$LIB_DIR"/*.jar "$WAR_TEMP_DIR/WEB-INF/lib"

mkdir -p "$WAR_TEMP_DIR/WEB-INF"
cp src/main/webapp/WEB-INF/web.xml "$WAR_TEMP_DIR/WEB-INF"


# Création du fichier WAR
cd "$WAR_TEMP_DIR"
jar -cvf "../$WAR_NAME" .
cd ..
rm -rf "$WAR_TEMP_DIR"
echo "Fichier WAR créé : $WAR_NAME."

# Déploiement du WAR dans Tomcat
cp "$WAR_NAME" "$WEBAPPS_DIR/"
echo "Fichier WAR copié dans $WEBAPPS_DIR."

# Redémarrage de Tomcat
sudo "$TOMCAT_HOME/bin/shutdown.sh"
sudo "$TOMCAT_HOME/bin/startup.sh"
echo "Tomcat redémarré."

echo "Déploiement terminé. Accédez à http://localhost:8080/$PROJECT_NAME"
