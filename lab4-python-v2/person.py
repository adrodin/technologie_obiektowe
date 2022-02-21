from __future__ import annotations
from vector import Vector
from vectorPolar import VectorPolar
from state import *
import random
import constants


class Person:
    _state = None

    def __init__(self, x, y, state):
        self.transition_to(state)
        self._position = Vector(x, y)
        angle, leng = VectorPolar.gen_random(constants.MAX_SPEED)
        self._move_vector = VectorPolar(leng, angle)
        #self._state = state
        self._days_to_recover = random.randint(500, 750)

    @property
    def state(self):
        return self._state

    def get_position(self):
        return self._position

    def get_state(self):
        return self._state

    def request_symptoms(self):
        self._state.handle_symptoms()

    def request_no_symptoms(self):
        self._state.handle_no_symptoms()

    def request_immune(self):
        self._state.handle_immune()

    def request_vulnerable(self):
        self._state.handle_vulnerable()

    def move(self):
        if random.uniform(0, 1) < 0.02:
            self._move_vector.random_vector(constants.MAX_SPEED)
        if not self._position.add_polar(self._move_vector):
            if random.uniform(0, 1) < 0.5:
                return False
            else:
                self._move_vector.random_vector(2.5)
                self.move()
        return True

    def distance(self, person):
        return self._position.distance(person.get_position())

    def transition_to(self, state):
        self._state = state
        self._state._person = self

    def transfer(self, person):
        if person.get_state() == SymptomsState():
            self.get_sick()
        elif person.get_state() == NoSymptomsState():
            if random.uniform(0, 1) < 0.5:
                self.get_sick()

    def get_sick(self):
        if random.uniform(0, 1) < 0.5:
            self.request_symptoms()
        else:
            self.request_no_symptoms()

    def recover(self):
        if self._state in [SymptomsState(), NoSymptomsState()]:
            self._days_to_recover -= 1
        if self._days_to_recover < 1:
            self.request_immune()
