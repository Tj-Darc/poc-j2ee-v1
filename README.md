
## POC J2EE V1 : Vehicule, Commentaire, JWT Token Authentification
 
Dans ce POC J2EE v1 j'implémente une application "Vehicle, Comment, JWT Token Authentication".

## Etape 1 - JWT Token Login

1. Enregistrement des utilisateurs : <br/>
– L'utilisateur utilise un Postman pour POST (s'inscrire) les informations de l'utilisateur (username, password, email, role) au Backend Rest API /poc/api/v1/register <br/>

2. Connexion de l'utilisateur : <br/>
– L'utilisateur POST user/password se connecter au Backend Rest API /poc/api/v1/login <br/>
– Backend vérifiera le user/password, s'il est correct, Backend créera une chaîne JWT avec un secret. <br/>

Après la connexion, l'utilisateur peut demander des ressources sécurisées au serveur principal en ajoutant le jeton JWT 
dans l'en-tête d'autorisation. Pour chaque demande, le backend vérifiera la signature JWT, puis restituera 
les ressources en fonction des autorités enregistrées de l'utilisateur. <br/>

Dans ce POC, nous exposons le REST API dans la documentation des API REST par Swagger UI - http://localhost:8080/poc/swagger-ui.html <br/>

## Etape 2 - Rest API pour Vehicule opérations <br/>
- POST véhicule (model, mark, picture) au Backend Rest API /poc/api/v1/vehicles  <br/>
- GET tous les véhicules au Backend Rest API /poc/api/v1/vehicles <br/>
- GET véhicules paginé au Backend Rest API /poc/api/v1/vehicles?page=0&size=2&sort=createdAt,desc <br/>

## Etape 3 - Rest API pour Commentaire opérations <br/>
- POST Comment à un véhicule (comment_data) au Backend Rest API /poc/api/v1/vehicles/{vehicleId}/comments <br/>
- GET tous les commentaire à un véhicule au Backend Rest API /poc/api/v1/vehicles/{vehicleId}/comments <br/>
- GET paginé commentaire à un véhicule au Backend Rest API /poc/api/v1/vehicles/{vehicleId}/comments?page=0&size=3&sort=createdAt,desc
