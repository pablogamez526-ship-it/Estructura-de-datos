import csv


NOMBRES_MESES = ["enero", "febrero", "marzo", "abril", "mayo", "junio",
                  "julio", "agosto", "septiembre", "octubre", "noviembre",
                  "diciembre"]


class RegistroMetrica:


    def __init__(self, red_social, concepto, valores_texto):
        self.__red_social = red_social
        self.__concepto = concepto
        self.__valores_mensuales = valores_texto  

    def get_red_social(self):
        return self.__red_social

    def get_concepto(self):
        return self.__concepto

    def get_valor(self, indice_mes):
        
        texto_valor = self.__valores_mensuales[indice_mes]
        return int(texto_valor)


def leer_csv(nombre_archivo):

    lista_registros = []
    archivo = open(nombre_archivo, "r", encoding="utf-8")
    lector = csv.reader(archivo)

    primera_fila = True
    for fila in lector:
        if primera_fila:
           primera_fila = False
            continue

        red_social = fila[0]
        concepto = fila[1]
        valores_meses = fila[3:15]  

        registro = RegistroMetrica(red_social, concepto, valores_meses)
        lista_registros.append(registro)

    archivo.close()
    return lista_registros


def buscar_registro(lista_registros, red_social, concepto):

    for registro in lista_registros:
        if registro.get_red_social() == red_social and registro.get_concepto() == concepto:
            return registro
    return None


def promedio(lista_valores):

    suma = 0
    for valor in lista_valores:
        suma = suma + valor
    return suma / len(lista_valores)
# ---------------------------------------------------------------------
# Diferencia de seguidores de Twitter entre enero y junio
# ---------------------------------------------------------------------
def mostrar_diferencia_followers_twitter(lista_registros):
    registro = buscar_registro(lista_registros, "TWITTER", "SEGUIDORES (FOLLOWERS)")

    seguidores_enero = registro.get_valor(0)  # 0 = enero
    seguidores_junio = registro.get_valor(5)  # 5 = junio
    diferencia = seguidores_junio - seguidores_enero

    print("--- Seguidores de Twitter ---")
    print("Enero:", seguidores_enero)
    print("Junio:", seguidores_junio)
    print("Diferencia (junio - enero):", diferencia)


# ---------------------------------------------------------------------
# El usuario elige dos meses y se calcula la diferencia de
# visualizaciones de YouTube entre esos meses
# ---------------------------------------------------------------------
def pedir_mes(mensaje):

    indice_mes = -1
    valido = False
    while valido == False:
        texto_mes = input(mensaje)
        texto_mes = texto_mes.lower()  # para aceptar "Junio", "JUNIO", "junio"

        indice_actual = 0
        for nombre_mes in NOMBRES_MESES:
            if nombre_mes == texto_mes:
                indice_mes = indice_actual
            indice_actual = indice_actual + 1

        if indice_mes != -1:
            valido = True
        else:
            print("Mes invalido. Escribe el nombre del mes en espanol (ejemplo: enero).")

    return indice_mes


def mostrar_diferencia_visualizaciones_youtube(lista_registros):
    registro = buscar_registro(lista_registros, "YOUTUBE", "VISUALIZACIONES")

    print("--- Visualizaciones de YouTube ---")
    print("Elige dos meses para comparar, escribiendo su nombre en minusculas(ejemplo: enero).")
    print("Nota: solo enero a junio tienen datos distintos de cero.")
    indice_mes1 = pedir_mes("Primer mes: ")
    indice_mes2 = pedir_mes("Segundo mes: ")

    visualizaciones_mes1 = registro.get_valor(indice_mes1)
    visualizaciones_mes2 = registro.get_valor(indice_mes2)
    diferencia = visualizaciones_mes2 - visualizaciones_mes1

    print("Visualizaciones en", NOMBRES_MESES[indice_mes1], ":", visualizaciones_mes1)
    print("Visualizaciones en", NOMBRES_MESES[indice_mes2], ":", visualizaciones_mes2)
    print("Diferencia:", diferencia)


# ---------------------------------------------------------------------
# Punto 4: promedio de crecimiento de Twitter y Facebook (enero-junio)
# ---------------------------------------------------------------------
def mostrar_promedio_crecimiento(lista_registros):
    registro_facebook = buscar_registro(lista_registros, "FACEBOOK", "CRECIMIENTO (seguidores)")
    registro_twitter = buscar_registro(lista_registros, "TWITTER", "CRECIMIENTO DE FOLLOWERS")

    valores_facebook = []
    valores_twitter = []
    indice = 0
    while indice <= 5:  # 0 = enero ... 5 = junio
        valores_facebook.append(registro_facebook.get_valor(indice))
        valores_twitter.append(registro_twitter.get_valor(indice))
        indice = indice + 1

    print("--- Promedio de crecimiento (enero-junio) ---")
    print("Facebook:", promedio(valores_facebook))
    print("Twitter:", promedio(valores_twitter))


# ---------------------------------------------------------------------
# Punto 5: promedio de "Me gusta" de YouTube, Twitter y Facebook
# --------------------------------------------------------------------

def mostrar_promedio_me_gusta(lista_registros):
    registro_facebook = buscar_registro(lista_registros, "FACEBOOK", "ME GUSTA EN PUBLICACIONES")
    registro_twitter = buscar_registro(lista_registros, "TWITTER", "ME GUSTA")
    registro_youtube = buscar_registro(lista_registros, "YOUTUBE", "ME GUSTA")

    valores_facebook = []
    valores_twitter = []
    valores_youtube = []
    indice = 0
    while indice <= 5:  # 0 = enero ... 5 = junio
        valores_facebook.append(registro_facebook.get_valor(indice))
        valores_twitter.append(registro_twitter.get_valor(indice))
        valores_youtube.append(registro_youtube.get_valor(indice))
        indice = indice + 1

    print("--- Promedio de Me Gusta (enero-junio) ---")
    print("Facebook:", promedio(valores_facebook))
    print("Twitter:", promedio(valores_twitter))
    print("YouTube:", promedio(valores_youtube))


# ---------------------------------------------------------------------
# Programa principal
# ---------------------------------------------------------------------
def main():
    lista_registros = leer_csv("datos.csv")

    mostrar_diferencia_followers_twitter(lista_registros)
    print()
    mostrar_diferencia_visualizaciones_youtube(lista_registros)
    print()
    mostrar_promedio_crecimiento(lista_registros)
    print()
    mostrar_promedio_me_gusta(lista_registros)


main()
