# Az euro karakter kiiratása HTML-ben, hogy biztonságosan megjelenjen.

# Beimportáljuk a Python beépített html modulját, ami segít HTML entitások kezelésében (például karakterek → HTML kód).
import html

# Példa karaktert definiálunk. Ebben az esetben az euró szimbólumot használjuk.
karakter = "€"

# Unicode kódpont
# Az ord() függvény megadja a karakter Unicode kódpontját.
# Az euró esetében a kódpont: 8364.
# Ez a szám az, amit a számítógép belsőleg használ a karakter azonosítására.
code_point = ord(karakter)

# HTML entitásnév
# A html.entities.codepoint2name egy szótár, ami visszaadja a karakter HTML entitás nevét a kódpont alapján.
# Például: 8364 → 'euro'.
entity_name = html.entities.codepoint2name[code_point]

# A valódi karaktert: €
print(f"A karakter: {karakter}")

# A HTML entitást, amit a HTML-ben használhatsz: &euro;
print(f"HTML entitás: &{entity_name};")
