# 🛒 Tienda Online de Electrónicos - Java Swing Application

¡Bienvenido al repositorio de la **Tienda Online de Electrónicos**! Esta aplicación es un sistema de venta de productos electrónicos implementado en Java utilizando patrones de diseño avanzados y una interfaz gráfica de usuario con Java Swing. La arquitectura y diseño de esta aplicación la convierten en un proyecto perfecto para explorar y aprender sobre patrones de diseño en programación orientada a objetos.

## 🚀 Características Principales

- **Selección de Productos**: Ofrece opciones para seleccionar entre laptops y smartphones, mostrando detalles específicos de cada producto.
- **Carrito de Compras**: Permite agregar productos al carrito, ver el total en tiempo real y procesar el pago.
- **Vaciar Carrito**: Proporciona la opción de vaciar el carrito sin necesidad de realizar una compra.
- **Procesamiento de Pago Simulado**: Integra un adaptador para simular el proceso de pago utilizando un servicio de pago externo.

## 🛠️ Patrones de Diseño Implementados

Este proyecto utiliza varios patrones de diseño de software:

1. **Singleton**: Para garantizar una única instancia de `CartManager` que maneja el carrito de compras.
2. **Factory Method**: Para la creación de productos electrónicos específicos como laptops y smartphones.
3. **Abstract Factory**: Para crear productos de una familia específica (por ejemplo, marcas como Apple).
4. **Builder**: Para construir productos personalizados con múltiples atributos.
5. **Adapter**: Para conectar y simular un servicio de pago externo.
6. **Decorator**: Para agregar características opcionales a productos, como garantías extendidas.

## 📂 Estructura del Proyecto

```plaintext
DESIGNPATTERNSDSI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── design_patterns/
│   │   │       ├── factory/           # Fábricas de productos
│   │   │       ├── singleton/         # Manejo del carrito (Singleton)
│   │   │       ├── adapter/           # Servicio de pago externo
│   │   │       ├── builder/           # Builder para productos personalizados
│   │   │       ├── decorator/         # Decorador para agregar características
│   │   │       ├── model/             # Clases de productos
│   │   │       ├── gui/               # Interfaz gráfica de usuario (GUI)
│   │   │          
└── README.md
```

## 🎨 Interfaz Gráfica de Usuario (GUI)

La GUI es intuitiva y fácil de usar. Los usuarios pueden:
- Seleccionar productos y ver detalles de cada uno antes de añadirlos al carrito.
- Ver en tiempo real el total acumulado en el carrito.
- Vaciar el carrito o procesar el pago total a través de un servicio de pago simulado.

## ⚙️ Tecnologías Utilizadas

- **Java SE**: La aplicación está desarrollada en Java y utiliza Java Swing para la GUI.
- **Patrones de Diseño**: Implementación avanzada de patrones de diseño en programación orientada a objetos.
- **Java Swing**: Para la creación de una interfaz gráfica sencilla y atractiva.

## 💻 Instalación y Ejecución

### Prerrequisitos
- **Java 8 o superior** debe estar instalado en tu sistema.
- **IDE de Java** (Eclipse, IntelliJ, o NetBeans) o usa la terminal con `javac` y `java`.

### Instrucciones

1. **Clona el repositorio** en tu máquina local:
   ```bash
   git clone https://github.com/Em3rc0d/designPatternsDSI.git
   ```
2. **Compila el proyecto**. Si usas la línea de comandos:
   ```bash
   cd designPatternsDSI/src/main/java
   javac design_patterns/GUI/StoreGUI.java
   ```
3. **Ejecuta la aplicación**:
   ```bash
   java design_patterns/GUI/StoreGUI
   ```
   
## 🧩 Ejemplo de Uso

1. **Seleccionar Producto**: Elige entre laptop o smartphone. Cada selección muestra los detalles del producto y permite agregarlo al carrito.
2. **Añadir al Carrito**: Los productos seleccionados se añaden al carrito y el total se actualiza en tiempo real.
3. **Procesar Pago**: Al finalizar, procesa el pago y recibe un mensaje de confirmación.
4. **Vaciar Carrito**: Limpia todos los artículos del carrito sin procesar el pago.

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Si deseas mejorar el proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una rama para tus cambios (`git checkout -b feature/tu-feature`).
3. Realiza un commit de tus cambios (`git commit -m 'Añade una nueva característica'`).
4. Haz push a la rama (`git push origin feature/tu-feature`).
5. Abre un Pull Request.
   
---

¡Gracias por visitar este repositorio! Si tienes alguna duda o sugerencia, no dudes en abrir un issue o contactarme. 😊

---
