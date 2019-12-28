# Okmány mikroszerviz

## Projekt leírás
Ez a projekt egy példafeladat megoldása.

A projektnél adott volt az *OkmanyDTO* objektum, amiknek mezőit kell validálja ez az alkalmazás. 


## Projekt indítása
A projekt Maven függőségkezelőt használ, így a `mvn install` parancs kiadása letölti a szükséges függőségeket.

### Konfiguráció
A mikroszerviz következő paramétereit lehet konfigurációból állítani:

- `kodszotar.filenev` A megadott kódszótár (reguláris kifejezésekkel kibővített) JSON fájl elérési útvonala 
- `okmanykep.w` Okmánykép elvárt szélessége, pixelekben
- `okmanykep.h` Okmánykép elvárt magassága, pixelekben 
- `okmanykep.tipusok` Elvárt okmánykép fájltípus (jpg, jpeg)

## Webszerviz leírás

### `okmany/ellenoriz`
Ez a végpont egy OkmanyDTO objektumot vár és ezt is ad vissza. Hiba esetén egy listát ad vissza
 
 
## Validáció
A program a validációt reguláris kifejezéssel végzi, amelyekhez a pattern az okmánytípus kódszótárban van.

## Tesztelés
A tesztelés egyszerűsítépséhez Swagger UI használható, ami program indítása után a `http://localhost:8001/swagger-ui` útvonalon érhető el.

A feladat unit, integrációs és átvételi tesztek készítését nem igényelte, ezért ezek elkészítésére nem került sor.

