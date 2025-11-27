# 🧪 Java AQA Testing Framework

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Groovy](https://img.shields.io/badge/Groovy-4.0-purple?logo=apachegroovy)
![Spock](https://img.shields.io/badge/Spock-2.4-blue?logo=spock)
![REST-Assured](https://img.shields.io/badge/REST--Assured-5.3-green)
![Gradle](https://img.shields.io/badge/Gradle-8.0%2B-lightblue?logo=gradle)

Проект автоматизированного тестирования на Java с использованием фреймворка Spock для unit-тестов и REST API тестирования.

## 📁 Структура проекта

```
├── build.gradle # Конфигурация Gradle с зависимостями
├── src/
│ └── test/
│ └── groovy/
│ ├── Calculator.spec.groovy # Unit-тесты калькулятора
│ └── ApiSpec.groovy # API тесты
└── src/main/java/
└── Calculator.java # Класс калькулятора
```

## 🛠 Технологии

- **Java 17+** - основной язык программирования
- **Groovy 4.0** - язык для написания тестов
- **Spock Framework 2.4** - BDD фреймворк для тестирования
- **REST Assured 5.3** - библиотека для REST API тестирования
- **Gradle** - система сборки
- **JUnit Platform** - запуск тестов

## 🧮 Компоненты проекта

### Calculator.java
Простой калькулятор с базовыми математическими операциями:
```java
Calculator calc = new Calculator();
calc.add(2, 3);        // → 5
calc.divide(10, 2);    // → 5
calc.divide(10, 0);    // → IllegalArgumentException
