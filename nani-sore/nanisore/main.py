import pygame
from pygame import mixer


pygame.init()
pygame.mixer.init()
mixer.set_num_channels(15)
nani = mixer.Sound("nani.wav")


def nani_sore():
    nani.play()


while 1:
    event = pygame.event.poll()
    if hasattr(event, 'key'):
        if event.key == pygame.K_q:
            break
    if event.type == pygame.KEYDOWN:
        nani_sore()
