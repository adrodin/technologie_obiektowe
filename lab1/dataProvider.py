import requests
import xml.etree.ElementTree as ET

DATA_URL = "https://www.nbp.pl/kursy/xml/lasta.xml"


class DataProvider:
    def __init__(self):
        pass

    @staticmethod
    def __get_data_from_web():
        response = requests.get(DATA_URL)
        response.encoding = 'ISO-8859-2'
        try:
            response.raise_for_status()
        except Exception as e:
            print(e)
        finally:
            return response.text

    @staticmethod
    def get_data(self):
        root = ET.fromstring(self.__get_data_from_web())
        list_to_send = []
        for e in root.findall("pozycja"):
            list_to_send.append({
                "name": e.find("nazwa_waluty").text,
                "code": e.find("kod_waluty").text,
                "course": float(e.find("kurs_sredni").text.replace(",", ".")),
                "converter": float((e.find("przelicznik").text.replace(",", ".")))
            })
        return list_to_send
