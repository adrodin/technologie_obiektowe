from copy import deepcopy
from abc import ABC, abstractmethod
from datetime import datetime


class PopulationMemento:
    def __init__(self, population, meetings, frame) -> None:
        self._population = deepcopy(population)
        self._meetings = deepcopy(meetings)
        self._frame = deepcopy(frame)

    def get_data(self):
        return self._population, self._meetings, self._frame


class Caretaker:

    def __init__(self, population) -> None:
        self._mementos = []
        self._population = population

    def backup(self) -> None:
        print("Caretaker: Saving Population's state...")
        self._mementos.append(self._population.save())

    def undo(self) -> None:
        if not len(self._mementos):
            return

        memento = self._mementos.pop()
        try:
            print(f"Caretaker: Restoring Population's... {len(self._mementos)} saved states left...")
            self._population.restore(memento)
        except Exception:
            self.undo()

