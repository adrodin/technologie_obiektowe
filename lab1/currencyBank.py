import xml.etree.ElementTree as ET
from currency import Currency
from datetime import datetime, timedelta
from dataSource import DataSource

DATA_URL = "https://www.nbp.pl/kursy/xml/lasta.xml"


def singleton(class_):
    instances = {}

    def getinstance(*args, **kwargs):
        if class_ not in instances:
            instances[class_] = class_(*args, **kwargs)
        return instances[class_]

    return getinstance


@singleton
class CurrencyBank:
    def __init__(self):
        self.__repository = {"PLN": Currency("złoty", "PLN", 1, 1)}
        self.__update()

    def __update(self):
        self.__update_time = datetime.now()
        for e in DataSource.get_data(DataSource()):
            if e['code'] in self.__repository.keys():
                self.__repository[e["code"]].set_course(e["course"], e["converter"])
            else:
                self.__repository[e["code"]] = Currency(e["name"], e["code"], e["course"], e["converter"])

    def get_currency_list(self):
        return self.__repository.keys()

    def get_currency_course(self, code):
        if self.__update_time + timedelta(minutes=15) < datetime.now():
            self.__update()
        if code in self.__repository.keys():
            return self.__repository[code].get_course()
        else:
            raise ValueError("This currency not exist.")

    def print_all_currencies(self):
        print("Kod waluty   Kurs waluty    Nazwa waluty")
        for key in self.__repository.keys():
            print(f'   {self.__repository[key].get_code()}       '
                  + "{:.6f}".format(self.__repository[key].get_course()) +
                  f'       {self.__repository[key].get_name()}')

    def is_code_in_bank(self, key):
        return key in self.__repository.keys()
