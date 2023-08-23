using System;
using System.Linq;

using System.IO;
namespace c_
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] arr = new int[10000];

            try
            {
                // Scanner nesnesini oluşturun
                using (StreamReader sr = new StreamReader("dizi.txt"))
                {
                    int i = 0;

                    // Dosyanın tüm satırlarını okuyun
                    while (!sr.EndOfStream)
                    {
                        string line = sr.ReadLine();
                        string[] parts = line.Split(' ');

                        foreach (string part in parts)
                        {
                            arr[i] = int.Parse(part);
                            i++;
                        }
                    }
                }
            }
            catch (FileNotFoundException)
            {
                Console.WriteLine("Dosya bulunamadı!");
            }
            catch (FormatException)
            {
                Console.WriteLine("Girdi dizgisi doğru biçimde değil!");
            }


double totalTime = 0;
long totalMemory = 0;

for (int i = 0; i < 100; i++)
{   int[] array = arr;
    // Ölçümleri başlatın
    
    long memoryBefore = System.GC.GetTotalMemory(false);
    long timeBefore = System.DateTime.Now.Ticks;

    // Fonksiyonu çalıştırın
      
    heapSort(array);
    // quickSort(array);
    // bubbleSort(array);
    // mergeSort(array);
    // selectionSort(array);

    // Ölçümleri bitirin
    long timeAfter = System.DateTime.Now.Ticks;
    long memoryAfter = System.GC.GetTotalMemory(false);

    // Çalışma süresini saniye cinsine çevirin
    double timeDiff = (timeAfter - timeBefore) / (double)1e9;
    long memoryDiff = memoryAfter - memoryBefore;

    // Toplam değerleri güncelle
    totalTime += timeDiff;
    totalMemory += memoryDiff;
}

// Ortalama değerleri hesapla
double ortalamaCalismaSuresi = totalTime / 100;
double ortalamaBellekKullanimi = totalMemory / 100;

// Ortalama değerleri ondalık gösterimde yazdırın
string ondalikGosterimCalismaSuresi = ortalamaCalismaSuresi.ToString("F10");
string ondalikGosterimBellekKullanimi = ortalamaBellekKullanimi.ToString();

Console.WriteLine("Ortalama calisma suresi: " + ondalikGosterimCalismaSuresi + " saniye");
Console.WriteLine("Ortalama toplam bellek kullanimi: " + ondalikGosterimBellekKullanimi + " byte");
        
  
        }



        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        private static int[] MergeSort(int[] arr)
        {
            // Dizinin boyutu 1'den küçükse, dizi zaten sıralıdır
            if (arr.Length <= 1)
            {
                return arr;
            }

            // Dizinin ortasını bul
            int middle = arr.Length / 2;

            // Dizinin sol yarısını al
            int[] left = arr.Take(middle).ToArray();

            // Dizinin sağ yarısını al
            int[] right = arr.Skip(middle).ToArray();

            // Sol ve sağ yarımı özyinelemeli olarak sırala
            left = MergeSort(left);
            right = MergeSort(right);

            // Sol ve sağ yarımı birleştir
            return Merge(left, right);
        }

        private static int[] Merge(int[] left, int[] right)
        {
            // Sonuç dizisi
            int[] result = new int[left.Length + right.Length];

            // İndisler
            int leftIndex = 0;
            int rightIndex = 0;
            int resultIndex = 0;

            // Sol ve sağ yarım hala eleman içeriyorsa
            while (leftIndex < left.Length && rightIndex < right.Length)
            {
                // Sol yarımın ilk elemanı sağ yarımın ilk elemanından küçükse
                if (left[leftIndex] < right[rightIndex])
                {
                    // Sonuç dizisine sol yarımın ilk elemanını ekle
                    result[resultIndex] = left[leftIndex];
                    // Sol yarımın indisini artır
                    leftIndex++;
                }
                else
                {
                    // Sonuç dizisine sağ yarımın ilk elemanını ekle
                    result[resultIndex] = right[rightIndex];
                    // Sağ yarımın indisini artır
                    rightIndex++;
                }

                // Sonuç dizisinin indisini artır
                resultIndex++;
            }

            // Eğer sol yarım hala eleman içeriyorsa, sonuç dizisine ekle
            while (leftIndex < left.Length)
            {
                result[resultIndex] = left[leftIndex];
                leftIndex++;
                resultIndex++;
            }

            // Eğer sağ yarım hala eleman içeriyorsa, sonuç dizisine ekle
            while (rightIndex < right.Length)
            {
                result[resultIndex] = right[rightIndex];
                rightIndex++;
                resultIndex++;
            }

            return result;
        }


 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public static int[] heapSort(int[] arr) {
        // Diziyi max heap'e dönüştürür
        int n = arr.Length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    
        // Bir sıraya diz
        int[] sortedArr = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sortedArr[i] = arr[0];
            arr[0] = arr[i];
            heapify(arr, i, 0);
        }
    
        return sortedArr;
    }
        private static void heapify(int[] arr, int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    // Eğer sol çocuk dizinin sınırlarını aşmıyorsa
    // ve sol çocuğun anahtarı, en büyük anahtardan büyükse
    if (l < n && arr[l] > arr[largest]) {
        largest = l;
    }

    // Eğer sağ çocuk dizinin sınırlarını aşmıyorsa
    // ve sağ çocuğun anahtarı, en büyük anahtardan büyükse
    if (r < n && arr[r] > arr[largest]) {
        largest = r;
    }

    // Eğer en büyük değer, yer değiştirme yapılması gereken değilse
    if (largest != i) {
        int swap = arr[i];
        arr[i] = arr[largest];
        arr[largest] = swap;

        // Max heap'in yapısını korur
        heapify(arr, n, largest);
    }
}


 ////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static int[] quickSort(int[] array) {
    // Eğer dizi 1 elemenden azsa, zaten sıralıdır
    if (array.Length <= 1) {
        return array;
    }

    // Pivot elemanını seç
    int pivot = array[0];

    // Pivot elemenden küçük olan elemanları depolamak için bir dizi oluştur
    int[] left = new int[array.Length];
    int leftSize = 0;
    foreach (int x in array) {
        if (x < pivot) {
            left[leftSize] = x;
            leftSize++;
        }
    }

    // Pivot elemanına eşit olan elemanları depolamak için bir dizi oluştur
    int[] equal = new int[array.Length];
    int equalSize = 0;
    foreach (int x in array) {
        if (x == pivot) {
            equal[equalSize] = x;
            equalSize++;
        }
    }

    // Pivot elemenden büyük olan elemanları depolamak için bir dizi oluştur
    int[] right = new int[array.Length];
    int rightSize = 0;
    foreach (int x in array) {
        if (x > pivot) {
            right[rightSize] = x;
            rightSize++;
        }
    }

    // Soldaki, eşit olan ve sağdaki dizileri yeniden sırala
    int[] leftSorted = quickSort(left[..leftSize]);
    int[] rightSorted = quickSort(right[..rightSize]);

    // Sıralı dizileri birleştir
    int[] sortedArray = new int[array.Length];
    Array.Copy(leftSorted, 0, sortedArray, 0, leftSorted.Length);
    Array.Copy(equal, 0, sortedArray, leftSorted.Length, equalSize);
    Array.Copy(rightSorted, 0, sortedArray, leftSorted.Length + equalSize, rightSorted.Length);
    return sortedArray;
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static int[] BubbleSort(int[] arr)
{
    // Dizinin elemanlarını sırayla karşılaştır
    for (int i = 0; i < arr.Length; i++)
    {
        for (int j = 0; j < arr.Length - i - 1; j++)
        {
            // Eğer ikili sırayla karşılaştırılan elemanlar yerlerini değiştirmek için uygunsa, yer değiştir
            if (arr[j] > arr[j + 1])
            {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
    return arr;
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static int[] selectionSort(int[] arr)
{
    // Dizinin uzunluğu
    int n = arr.Length;

    // Her bir eleman için
    for (int i = 0; i < n; i++)
    {
        // En küçük elemanı bul
        int minIdx = i;
        for (int j = i + 1; j < n; j++)
        {
            if (arr[minIdx] > arr[j])
            {
                minIdx = j;
            }
        }
        // En küçük elemanı en başa taşı
        int temp = arr[i];
        arr[i] = arr[minIdx];
        arr[minIdx] = temp;
    }
    return arr;
}


    }
}
