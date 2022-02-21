from button import Button
import pygame
import constants


class Menu:
    def __init__(self, screen, clock):
        self._screen = screen
        self._clock = clock
        self._init_buttons()

    def _init_buttons(self):
        self._start = Button(100,320,200,100,text="Start")
        self._immune = Button(720,320,200,100,text="Immune",color=constants.RED)

    def show(self):
        self._start.draw(self._screen)
        self._immune.draw(self._screen)
        running = True
        while running:
            self._clock.tick(48)
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                if event.type == pygame.MOUSEBUTTONDOWN:
                    answer = self.actions_on_click(pygame.mouse.get_pos())
                    if answer != 0:
                        return self._immune.get_color() == constants.GREEN
            pygame.display.update()

    def actions_on_click(self,mouse_position):
        if self._start.is_mouse_over(mouse_position):
            return 1
        if self._immune.is_mouse_over(mouse_position):
            if self._immune.get_color() == constants.RED:
                self._immune.change_color(constants.GREEN, self._screen)
            else:
                self._immune.change_color(constants.RED, self._screen)
            return 0
        return 0