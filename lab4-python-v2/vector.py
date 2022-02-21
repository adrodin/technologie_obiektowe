from math import sin, cos, sqrt
from vectorPolar import VectorPolar
import constants


class Vector:
    def __init__(self, x, y):
        self.__x = x
        self.__y = y

    def get_cords(self):
        return self.__x, self.__y

    def add_polar(self, vectorPolar: VectorPolar):
        length, angle = vectorPolar.get()
        self.__x += length*sin(angle)
        self.__y += length*cos(angle)
        return (0 < self.__x < constants.WIDTH) and (0 < self.__y < constants.HEIGHT)

    def distance(self, vector):
        x, y = vector.get_cords()
        return sqrt((x-self.__x)*(x-self.__x)+(y-self.__y)*(y-self.__y))