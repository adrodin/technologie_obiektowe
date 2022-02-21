import constants
import pygame
from menu import Menu
from simulation import Simulation

pygame.init()
screen = pygame.display.set_mode([1080, 720])
screen.fill(constants.BLACK)
clock = pygame.time.Clock()
menu = Menu(screen, clock)
cb = menu.show()
running = True
sim = Simulation(screen, clock, cb)
sim.run()
pygame.quit()
