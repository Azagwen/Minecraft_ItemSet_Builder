# Minecraft_ItemSet_Builder

Application that creates recipes and item models for Minecraft 1.16.X mods

#### The app will ask for :

- A Material (ex: iron, gold...). 
- A tools, armor items and block texture folders **name**.
- The mod's namespace.
- A item type (ex: ingot...) for the recipe "M" ingredient.
- And your output folder.

The texture folders and namespace will be defaulted to Minecraft's vanilla values if left empty. (e.g. `item/`, `block/` and `minecraft:`).
The ingredient type will return the Material alone if left empty (ex: diamond), if filled however, it will return `Material_[type]`.
