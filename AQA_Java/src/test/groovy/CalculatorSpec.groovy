import spock.lang.Specification

class CalculatorSpec extends Specification{
    def calculator

    def setup(){
        calculator = new Calculator()
    }

    def "Сложение двух чисел"(){
        when:
        def result = calculator.add(2,3)

        then:
        result == 5
    }

    def "Успешное деление" (){
        when: "Делим 10 на 2"
        def result = calculator.divide(10,2)

        then: "Получаем 5"
        result == 5
    }

    def "Ошибка при делении на ноль"(){
        when:
        def result = calculator.divide(10,0)

        then:
        thrown(IllegalArgumentException)
    }
}
