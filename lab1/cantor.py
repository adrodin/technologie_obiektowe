from currencyBank import CurrencyBank


def is_float(value):
    try:
        float(value)
        return True
    except ValueError:
        return False


class Cantor:
    def __init__(self):
        self.__currency_bank = CurrencyBank()

    def run(self):
        flag = True
        while flag:
            print("1. Wymiana")
            print("2. Wyjście")
            flag = self.__input_logic()

    def __input_logic(self):
        answer = input()
        if answer == '1':
            self.__exchange_logic()
            return True
        elif answer == '2':
            print("Koniec działania aplikacji....")
            return False
        else:
            print("Nie ma takiej opcji")
            return True

    def __input_currency_code(self):
        currency_code = input().upper()
        while not self.__currency_bank.is_code_in_bank(currency_code):
            print("Brak kodu waluty")
            print("Wprowadź kod waluty")
            currency_code = input().upper()
        return currency_code

    def __input_float(self):
        inp = input().replace(',', '.')
        while not is_float(inp):
            print("Podana wartość musi być liczbą")
            print("Podaj wartość")
            inp = input().replace(',', '.')
        return float(inp)

    def __exchange_logic(self):
        print("Aktualne kursy:")
        self.__currency_bank.print_all_currencies()
        print("Wprowadź kod wejściowej waluty")
        currency_in = self.__input_currency_code()
        print("Podaj wartość waluty wejściowej")
        value_in = self.__input_float()
        print("Wprowadź kod wyjściowej waluty")
        currency_out = self.__input_currency_code()
        value_out = self.__exchange(currency_in, value_in, currency_out)
        print(f"{value_in} {currency_in} to {value_out} {currency_out}")

    def __exchange(self, currency_in, value, couurency_out):
        currency_in_course = self.__currency_bank.get_currency_course(currency_in)
        currency_out_course = self.__currency_bank.get_currency_course(couurency_out)
        return round(value * currency_in_course / currency_out_course, 2)
