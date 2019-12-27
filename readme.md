# Okmány mikroszerviz

## Projekt leírás
Ez a projekt egy példafeladat megoldása.

A projektnél adott volt az *OkmanyDTO* objektum, amiknek mezőit kell validálja ez az alkalmazás. 


## Projekt indítása
A projekt Maven függőségkezelőt használ, így a `mvn install` parancs kiadása letölti a szükséges függőségeket.

## Webszerviz leírás

### `okmany/ellenoriz`
Ez a végpont egy OkmanyDTO objektumot vár és ezt is ad vissza. Hiba esetén egy listát ad vissza
 
## Tesztelés
A tesztelés egyszerűsítépséhez Swagger UI használható, ami program indítása után a `http://localhost:8001/swagger-ui` útvonalon érhető el.

