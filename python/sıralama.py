def merge_sort(arr):
    """
    Bu fonksiyon, verilen dizinin elemanlarını ikiye bölerek sıralar.
    """
    # Dizinin boyutu 1'den küçükse, dizi zaten sıralıdır
    if len(arr) <= 1:
        return arr
    
    # Dizinin ortasını bul
    middle = len(arr) // 2
    
    # Dizinin sol yarısını al
    left = arr[:middle]
    
    # Dizinin sağ yarısını al
    right = arr[middle:]
    
    # Sol ve sağ yarımı özyinelemeli olarak sırala
    left = merge_sort(left)
    right = merge_sort(right)
    
    # Sol ve sağ yarımı birleştir
    return merge(left, right)

def merge(left, right):
    """
    Bu fonksiyon, iki parametre olarak verilen sol ve sağ yarımları
    sırayla karşılaştırarak birleştirir ve sonuç olarak sıralı bir dizi
    döndürür.
    """
    result = []
    # Sol ve sağ yarım hala eleman içeriyorsa
    while (left and right):
        # Sol yarımın ilk elemanı sağ yarımın ilk elemanından küçükse
        if left[0] < right[0]:
            # Sonuç dizisine sol yarımın ilk elemanını ekle
            result.append(left[0])
            # Sol yarımdan ilk elemanı çıkar
            left.pop(0)
        else:
            # Sonuç dizisine sağ yarımın ilk elemanını ekle
            result.append(right[0])
            # Sağ yarımdan ilk elemanı çıkar
            right.pop(0)
    # Eğer sol yarım hala eleman içeriyorsa, sonuç dizisine ekle
    if left:
        result += left
    # Eğer sağ yarım hala eleman içeriyorsa, sonuç dizisine ekle
    if right:
        result += right
    return result

################################################################################################################################

def heap_sort(arr):
    # Diziyi max heap'e dönüştürür
    n = len(arr)
    for i in range(n, -1, -1):
        heapify(arr, n, i)
 
    # Bir sıraya diz
    sorted_arr = []
    for i in range(n-1, -1, -1):
        arr[i], arr[0] = arr[0], arr[i]   # swap
        sorted_arr.append(arr[i])
        heapify(arr, i, 0)
 
    return sorted_arr[::-1]  # Sıralanmış diziyi ters çevirir
 
def heapify(arr, n, i):
    largest = i  
    l = 2 * i + 1     
    r = 2 * i + 2     
 
    # Eğer sol çocuk dizinin sınırlarını aşmıyorsa
    # ve sol çocuğun anahtarı, en büyük anahtardan büyükse
    if l < n and arr[i] < arr[l]:
        largest = l
 
    # Eğer sağ çocuk dizinin sınırlarını aşmıyorsa
    # ve sağ çocuğun anahtarı, en büyük anahtardan büyükse
    if r < n and arr[largest] < arr[r]:
        largest = r
 
    # Eğer en büyük değer, yer değiştirme yapılması gereken değilse
    if largest != i:
        arr[i],arr[largest] = arr[largest],arr[i]  # swap
 
        # Max heap'in yapısını korur
        heapify(arr, n, largest)
        
      
 ################################################################################################################################       
        
def quick_sort(arr):
    if len(arr) <= 1:
        return arr
    pivot = arr[len(arr) // 2]
    left = [x for x in arr if x < pivot]
    middle = [x for x in arr if x == pivot]
    right = [x for x in arr if x > pivot]
    return quick_sort(left) + middle + quick_sort(right)


################################################################################################################################


def bubble_sort(arr):
    
    # Dizinin elemanlarını sırayla karşılaştır
    for i in range(len(arr)):
        for j in range(0, len(arr) - i - 1):
            # Eğer ikili sırayla karşılaştırılan elemanlar yerlerini değiştirmek için uygunsa, yer değiştir
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr
                

################################################################################################################################ 

               
def selection_sort(arr):
    
    # Dizinin uzunluğu
    n = len(arr)

    # Her bir eleman için
    for i in range(n):
        # En küçük elemanı bul
        min_idx = i
        for j in range(i + 1, n):
            if arr[min_idx] > arr[j]:
                min_idx = j
        # En küçük elemanı en başa taşı
        arr[i], arr[min_idx] = arr[min_idx], arr[i]
    return arr
        

################################################################################################################################ 



import psutil
import time



# diziyi oluşturma

with open('dizi.txt', 'r') as dosya:
    # dosyadaki verileri okuyun
    veriler = dosya.read()
    veriler_dizi = veriler.split()
    
arr = []

for veri in veriler_dizi:
    sayi = int(veri)
    arr.append(sayi)



#dizi sıralama 

#Hız ve bellek testi

totaltime = 0
totalmemory = 0
for i in range(100):

    array = arr
    before_function = psutil.virtual_memory().used # çalıştırmadan önce kullanılan bellek miktarı
    time_before = time.time() # çalıştırmadan önceki zaman

    heap_sort(array)
    # quick_sort(array)
    # bubble_sort(array)
    # merge_sort(array)
    selection_sort(array)

    time_after = time.time() # çalıştırmadan sonraki zaman
    süre = time_after - time_before # fonksiyonun çalışma süresi

        
    after_function = psutil.virtual_memory().used # çalıştırmadan sonra kullanılan bellek miktarı
    function_memory_usage = after_function - before_function # fonksiyon çalıştırıldıktan sonra kullanılan bellek miktarı
        
    totaltime+= süre
    totalmemory += function_memory_usage


print("Çalışma süresi: " ,abs(totaltime/100) ," saniye")
print("Toplam bellek kullanımı: " , abs(int(totalmemory/100)) , " byte")
    
 








