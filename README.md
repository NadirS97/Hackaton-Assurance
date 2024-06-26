# Projet Hackaton Assurance

### Fait par le groupe 2 :
- Anas MOUSSAOUI 
- El Ghali TAHRI JOUTEY
- Mohamed-Hamza AIT BENAISSA
- Nadir SAIAH
- Yohann CHOLLEY
- Mohammed ET-TAZY
  
### Année : Master 2 MIAGE - 2024
------------

**Contexte :** Proposition et développement d'une idée innovante dans le monde de l'assurance lors d'un hackaton, sous forme d'un sprint de 3 jours. 

**Notre idée :** Security Score Assurance est une assurance qui récompense la bonne conduite sur des trajets de courtes distances tout au long d'un mois, via un système de points, permettant ainsi d'obtenir des réductions sur le prix de l'assurance automobile.

**Système de points :**
Initialement chaque assuré possède 1000 points par mois
- Perte d'un point pour chaque 2 km effectués
- Perte d'un point pour chaque infraction effectuée (une forte accélération ou un freinage brusque)

**Matériel :**
- Capteur : Accélérometre
- GPS

**API utilisée :**
- API Sensor [documentation](https://developer.mozilla.org/en-US/docs/Web/API/Accelerometer) 
- API Geolocalisation [documentation](https://developer.mozilla.org/fr/docs/Web/API/Geolocation_API)

------------
Mise en place de l'environnement de travail :
----------------

**1-** On lance tout d'abord l'application **Docker Desktop** :

Lien de téléchargement : [ici](https://www.docker.com/products/docker-desktop/)

**2-** On démarre nos conteneurs docker (Un pour le Back et un pour le Front) :

Il suffit d'executer le script bash start.sh : script permettant d'effectuer ``` docker compose up -d --build ```

Back :
>- Nom du conteneur : *projet*
>- Port utilisé : *8081*
>- Build:Context : on va build une image via le Dockerfile situé dans : *./back/*

Front:
>- Nom du conteneur : *front*
>- Port utilisé : *4200*
>- Build:Context : on va build une image via le Dockerfile situé dans : *./front/hackaton_project/*
>- depends_on : projet (afin de s'assurer que le Back est bien lancé et fonctionnel avant de start le Front)

**3-** Vous avez maintenant accés à notre application :

Il suffit d'aller sur [http://localhost:4200/connexion](http://localhost:4200/connexion)

Si vous rencontrer une difficulté avec docker :
Vous pouvez opter pour un lancement manuel 
- Pour le back via soit le run du main de HackatonAssuranceApplication soit en executant la commande ```mvn spring-boot:run```
- Pour le front il suffit de se positionner dans le repertoire : /front/hackaton_project/ et executer d'abord la commande ```npm i``` puis ```ng serve```




