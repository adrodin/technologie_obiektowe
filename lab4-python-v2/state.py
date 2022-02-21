from __future__ import annotations
from abc import ABC, abstractmethod
from person import *


class State(ABC):
    @property
    def person(self):
        return self._person

    @person.setter
    def person(self, person) -> None:
        self._person = person

    @abstractmethod
    def handle_symptoms(self) -> None:
        pass

    @abstractmethod
    def handle_no_symptoms(self) -> None:
        pass

    @abstractmethod
    def handle_immune(self) -> None:
        pass

    @abstractmethod
    def handle_vulnerable(self) -> None:
        pass

    def __eq__(self, other):
        if isinstance(other, State):
            return self.__class__ == other.__class__
        return False


class ImmuneState(State):
    def handle_symptoms(self) -> None:
        pass

    def handle_no_symptoms(self) -> None:
        pass

    def handle_immune(self) -> None:
        pass

    def handle_vulnerable(self) -> None:
        pass


class VulnerableState(State):
    def handle_symptoms(self) -> None:
        self.person.transition_to(SymptomsState())

    def handle_no_symptoms(self) -> None:
        self.person.transition_to(NoSymptomsState())

    def handle_immune(self) -> None:
        pass

    def handle_vulnerable(self) -> None:
        pass


class SymptomsState(State):
    def handle_symptoms(self) -> None:
        pass

    def handle_no_symptoms(self) -> None:
        pass

    def handle_immune(self) -> None:
        self.person.transition_to(ImmuneState())

    def handle_vulnerable(self) -> None:
        pass


class NoSymptomsState(State):
    def handle_symptoms(self) -> None:
        pass

    def handle_no_symptoms(self) -> None:
        pass

    def handle_immune(self) -> None:
        self.person.transition_to(ImmuneState())

    def handle_vulnerable(self) -> None:
        pass




