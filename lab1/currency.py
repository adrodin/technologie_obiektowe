
class Currency:
    def __init__(self, name, code, course, multiplier):
        self.__name = name
        self.__code = code
        self.__course = round(course/multiplier, 6)

    def get_name(self):
        return self.__name

    def get_code(self):
        return self.__code

    def get_course(self):
        return self.__course

    def set_course(self, course, multiplier):
        self.__course = round(course / multiplier, 6)
