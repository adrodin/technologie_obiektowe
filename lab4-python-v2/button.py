import pygame



class Button:
    def __init__(self,x,y,width,height,color=(255,255,255),text='',text_color=(0,0,0)):
        """
        Inicjalizacja przycisku
        :param x: pozycja x przycisku
        :param y: pozycja y przycisku
        :param width: szerokość przycisku
        :param height: wysokość przycisku
        :param color: kolor przycisku
        :param text: teskt przycisku
        :param text_color: kolor tekstu w przycisku
        """
        self._x = x
        self._y = y
        self._text = text
        self._width = width
        self._height = height
        self._color = color
        self._text_color = text_color


    def is_mouse_over(self,mouse_position):
        """
        Sprawdzenie pozycji myszy względem przycisku
        :param mouse_position: pozycja myszki zapisana jako tuple (x,y)
        :return: True jeżeli myszka jest nad przyciskie, False w przeciwnym wypadku
        """
        is_mouse_inside_x = self._x < mouse_position[0] < self._x + self._width
        is_mouse_inside_y = self._y < mouse_position[1] < self._y + self._height
        return is_mouse_inside_x and is_mouse_inside_y

    def draw(self, window):
        """
        Narysowanie przycisku
        :param window: okno w którym ryoswany będzie przycisk
        """
        pygame.draw.rect(window, self._color, (self._x, self._y, self._width, self._height), 0)
        if self._text != '':
            font = pygame.font.SysFont('Arial', 25)
            text = font.render(self._text, True, self._text_color)
            window.blit(text, (self._x + (self._width// 2 - text.get_width() // 2),self._y + (self._height // 2 - text.get_height() // 2)))


    def change_color(self,color,window):
        """
        Zmiana koloru
        :param color: nowy kolor przycisku
        :param window: okno w, w którym rysowany będzie przycisk
        """
        self._color = color
        self.draw(window)

    def change_text(self,new_text,window):
        """
        Zmiana tekstu
        :param new_text: nowy tekst wyświetlany w przycisku
        :param window: okno w, w którym rysowany będzie przycisk
        """
        self._text = new_text
        self.draw(window)

    def change_text_with_new_color(self,new_text,new_color,window):
        """
        Zmiana tekstu wraz z kolorem
        :param new_text: nowy tekst wyświetlany w przycisku
        :param new_color: nowy kolor tekstu wyświetlanego w przycisku
        :param window: okno w, w którym rysowany będzie przycisk
        """
        self._text = new_text
        self._text_color = new_color
        self.draw(window)

    def get_color(self):
        return self._color