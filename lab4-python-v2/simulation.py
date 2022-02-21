from button import Button
from population import Population
import constants
import pygame
from state import *
from memento import Caretaker


class Simulation:
    def __init__(self, screen, clock, immune):
        self._screen = screen
        self._clock = clock
        self._population = Population(0.1 if immune else 0)
        self._save_button = Button(950, 20, 100, 50, text="Save")
        self._restore_button = Button(950, 90, 100, 50, text="Restore")
        self._caretaker = Caretaker(self._population)

    def run(self):
        running = True
        while running:
            #print(f"FPS: {int(self._clock.get_fps())} {len(self._population.get_persons())}")

            self._screen.fill(constants.BLACK)
            self._clock.tick(26)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                if event.type == pygame.MOUSEBUTTONDOWN:
                    if self._save_button.is_mouse_over(pygame.mouse.get_pos()):
                        self._caretaker.backup()
                    if self._restore_button.is_mouse_over(pygame.mouse.get_pos()):
                        self._caretaker.undo()

            self._population.step()
            for person in self._population.get_persons():
                self.draw(person)
            self._save_button.draw(self._screen)
            self._restore_button.draw(self._screen)
            pygame.display.update()

    def draw(self, person):
        if person.get_state() == ImmuneState():
            pygame.draw.circle(self._screen, constants.BLUE, person.get_position().get_cords(), constants.PERSON_SIZE)
        elif person.get_state() == SymptomsState():
            pygame.draw.circle(self._screen, constants.RED, person.get_position().get_cords(), constants.PERSON_SIZE)
        elif person.get_state() == NoSymptomsState():
            pygame.draw.circle(self._screen, constants.ORANGE, person.get_position().get_cords(), constants.PERSON_SIZE)
        elif person.get_state() == VulnerableState():
            pygame.draw.circle(self._screen, constants.GREEN, person.get_position().get_cords(), constants.PERSON_SIZE)
        else:
            pygame.draw.circle(self._screen, constants.WHITE, person.get_position().get_cords(), constants.PERSON_SIZE)
