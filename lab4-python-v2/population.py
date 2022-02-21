from person import Person
import constants
import random
from state import *
from memento import PopulationMemento
from copy import deepcopy
import time
import sys


class Population:
    def __init__(self, immune):
        self._immune = immune
        self._persons = [
            Person(
                random.randint(0, constants.WIDTH),
                random.randint(0, constants.HEIGHT),
                ImmuneState() if random.random() < immune else VulnerableState()
            )
            for _ in range(constants.POOPULATION)
        ]
        self._meetings = {}
        self._frame = 0

    def _persons_move(self):
        for person in self._persons:
            person.recover()
            if not person.move():
                self._persons.remove(person)

    def _get_suspects(self):
        return list(filter(lambda p: p.get_state() in [SymptomsState(), NoSymptomsState()], self._persons))

    def step(self):
        self._frame += 1
        self._persons_move()
        self._meetings_checker()
        self._generate_new_person()

    def restore(self, memento: PopulationMemento):
        data = deepcopy(memento.get_data())
        if len(data) > 0:
            self._persons = deepcopy(data[0])
            self._meetings = deepcopy(data[1])
            self._frame = deepcopy(data[2])

    def save(self) -> PopulationMemento:
        return PopulationMemento(self._persons, self._meetings, self._frame)

    def _meetings_checker(self):
        suspects = self._get_suspects()
        #print(len(suspects))
        for person in self._persons:
            if person.get_state() == VulnerableState():
                for p in suspects:
                    if person.distance(p) < constants.CONTACT_DISTANCE:
                        if person in self._meetings:
                            if self._meetings[person][0] >= (self._frame-1):
                                self._meetings[person][1] += 10
                            else:
                                self._meetings[person][1] = 1
                            self._meetings[person][0] = self._frame
                        else:
                            self._meetings[person] = [self._frame, 2]
                            self._meetings[person][0] = self._frame
                        if self._meetings[person][1] >= 75:
                            person.transfer(p)
                            self._meetings[person][1] = 0
                        break

    def _generate_new_person(self):
        if random.uniform(0, 1) < constants.NEW_PERSON_P:
            if random.uniform(0, 1) < 0.5:
                x = random.choice([0, constants.WIDTH])
                y = random.uniform(0, constants.HEIGHT)
            else:
                x = random.uniform(0, constants.WIDTH)
                y = random.choice([0, constants.HEIGHT])
            self._persons.append(
                Person(
                    x,
                    y,
                    #ImmuneState() if random.random() < self._immune else VulnerableState()
                    ImmuneState() if random.random() < self._immune else SymptomsState() if random.random() < 0.1 else VulnerableState()
                )
            )

    def get_persons(self):
        return self._persons
