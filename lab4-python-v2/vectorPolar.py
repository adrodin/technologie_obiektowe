import random
import math
import constants


class VectorPolar:
    def __int__(self):
        self.random_vector(constants.MAX_SPEED)

    def __init__(self, length, angle):
        self._length = length
        self._angle = angle

    def get(self):
        return self._length, self._angle

    def random_vector(self, max_length):
        self._angle = random.uniform(0, 2 * math.pi)
        self._length = random.uniform(0, max_length)

    @staticmethod
    def gen_random(max_length):
        return random.uniform(0, 2 * math.pi), random.uniform(0, max_length)


