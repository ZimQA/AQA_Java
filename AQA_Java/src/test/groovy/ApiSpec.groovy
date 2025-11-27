import io.restassured.RestAssured // библиотека для http запросов
import spock.lang.Specification // spock фрейм для тестов

class ApiSpec  extends Specification {

    def setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com"
    }

    def "should get user by id"() {
        when: "отправляем get запрос на эндпоинт /users/1"
        def response = RestAssured.given()
                .when()
                .get("/users/1")
                .then()
                .extract().response()

            then: "ответ должен иметь статус 200 и корректные данные"
            response.statusCode() == 200
            response.jsonPath().getString("name") == "Leanne Graham"
            response.jsonPath().getInt("id") == 1
    }

    def "should create new user by POST"() {
        given: "данные пользователя для создания"
        def userData = '''
        {
           "name": "Test User",
           "email": "test@example.com"
        }
        '''

        when: "отправляем POST запрос на создание юзера"
        def response = RestAssured.given()
                .contentType("application/json") // отправляем JSON
                .body(userData) // передаем данные
                .when()
                .post("/users")
                .then()
                .extract().response()

        then: "успешное создание пользователя"
        response.statusCode() == 201
        response.jsonPath().getString("name") == "Test User"
        response.jsonPath().getString("email") == "test@example.com"
        response.jsonPath().getInt("id") > 0

    }

    def "should get user by id [#userId]"() {
        when: "отправляем get запрос на эндпоинт /users/#${userId}"
        def response = RestAssured.given()
                .when()
                .get("/users/${userId}")
                .then()
                .extract().response()

        then: "ответ должен иметь статус 200 и корректные данные"
        response.statusCode() == 200
        response.jsonPath().getInt("id") == userId
        response.jsonPath().getString("name") == expectedName
        // println "Ожидаемое имя: ${expectedName}, Полученное имя: ${response.jsonPath().getString('name')}"

        where:
        userId | expectedName
        1      | "Leanne Graham"
        2      | "Ervin Howell"
        3      | "Clementine Bauch"
        // userId << [1,2,3]
    }

    def "должен вернуть 404 на несуществующего юзера" (){
        when: "запрашиваем инфу несущ. пользака"
        def response = RestAssured.given()
                .when()
                .get("/users/99999")
                .then()
                .extract().response()

        then: "ответ должен иметь 404"
        response.statusCode() == 404
    }

    def "проверка валидации структуры пользака" (){
        when: "запрашиваем данные пользователя"
        def response = RestAssured.get("/users/1")

        then: "проверяем всю структуру пользователя"
        with(response.jsonPath()){
            getInt("id") == 1
            getString("name") != null
            getString("email").contains("@")
            getString("address.city") != null
            getMap("company").size() > 0
        }
    }
}