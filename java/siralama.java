import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.lang.management.ManagementFactory;

import java.lang.management.*;
    
class siralama {
    public static void main(String[] args) {
       
          // Dosya yolunu ve adını belirtin
          File file = new File("dizi.txt");
          int[] arr = new int[10000];
            
          try {
              // Scanner nesnesini oluşturun
              Scanner sc = new Scanner(file);
              int i = 0;
  
              // Dosyanın tüm satırlarını okuyun
              while (sc.hasNextLine()) {
                  String line = sc.nextLine();
                  String[] parts = line.split(" ");
  
                  for (String part : parts) {
                      arr[i] = Integer.parseInt(part);
                      i++;
                  }
              }
  
              // Scanner nesnesini kapatın
              sc.close();
          } catch (FileNotFoundException e) {
              System.out.println("Dosya bulunamadı!");
          }
     
    double totalTime = 0;
    long totalMemory = 0;
    for (int i = 0; i < 100; i++){
        int[] array = arr;
        // Ölçümleri başlatın
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsageBefore = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsageBefore = memoryBean.getNonHeapMemoryUsage();

        long beforeFunction = heapUsageBefore.getUsed() + nonHeapUsageBefore.getUsed();
        long timeBefore = System.nanoTime();
    
        // Fonksiyonu çalıştırın
        
       
        heapSort(array);
        //quickSort(array);
        //bubbleSort(array);
        //mergeSort(array);
        //selectionSort(array);

        // Ölçümleri bitirin
        long timeAfter = System.nanoTime();
        MemoryUsage heapUsageAfter = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsageAfter = memoryBean.getNonHeapMemoryUsage();

        long afterFunction = heapUsageAfter.getUsed() + nonHeapUsageAfter.getUsed();
        long functionMemoryUsage = afterFunction - beforeFunction;
    
        // Çalışma süresini saniye cinsine çevirin
        double timeDiff = (timeAfter - timeBefore) / 1e9;
        
        // Toplam değerleri güncelle
        totalTime += timeDiff;
        totalMemory += functionMemoryUsage;
    }
    
// Ortalama değerleri hesapla
double ortalamaCalismaSuresi = totalTime / 100;
long ortalamaBellekKullanimi = totalMemory / 100;
DecimalFormat df = new DecimalFormat("0.0000000000");
String oCalismaSuresi = df.format( Math.abs(ortalamaCalismaSuresi));   

    // Sonuçları yazdırın
    
    System.out.println("Calisma suresi: " +oCalismaSuresi + " saniye");
    System.out.println("Toplam bellek kullanimi: " + Math.abs(ortalamaBellekKullanimi ) + " byte");
    

    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static int[] mergeSort(int[] arr) {
        // Dizinin boyutu 1'den küçükse, dizi zaten sıralıdır
        if (arr.length <= 1) {
            return arr;
        }
        
        // Dizinin ortasını bul
        int middle = arr.length / 2;
        
        // Dizinin sol yarısını al
        int[] left = new int[middle];
        System.arraycopy(arr, 0, left, 0, middle);
        
        // Dizinin sağ yarısını al
        int[] right = new int[arr.length - middle];
        System.arraycopy(arr, middle, right, 0, arr.length - middle);
        
        // Sol ve sağ yarımı özyinelemeli olarak sırala
        left = mergeSort(left);
        right = mergeSort(right);
        
        // Sol ve sağ yarımı birleştir
        return merge(left, right);
    }
    
    public static int[] merge(int[] left, int[] right) {
        // Sonuç dizisini oluştur
        int[] result = new int[left.length + right.length];
        
        // İndisleri tut
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        
        // Sol ve sağ yarım hala eleman içeriyorsa
        while (leftIndex < left.length && rightIndex < right.length) {
            // Sol yarımın ilk elemanı sağ yarımın ilk elemanından küçükse
            if (left[leftIndex] < right[rightIndex]) {
                // Sonuç dizisine sol yarımın ilk elemanını ekle
                result[resultIndex] = left[leftIndex];
                leftIndex++;
            } else {
                // Sonuç dizisine sağ yarımın ilk elemanını ekle
                result[resultIndex] = right[rightIndex];
                rightIndex++;
            }
            resultIndex++;
        }
        
        // Eğer sol yarım hala eleman içeriyorsa, sonuç dizisine ekle
        while (leftIndex < left.length) {
            result[resultIndex] = left[leftIndex];
            leftIndex++;
            resultIndex++;
        }
        
        // Eğer sağ yarım hala eleman içeriyorsa, sonuç dizisine ekle
        while (rightIndex < right.length) {
            result[resultIndex] = right[rightIndex];
            rightIndex++;
            resultIndex++;
        }
        
        return result;
    }
      //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      public static int[] heapSort(int[] arr) {
        // Diziyi max heap'e dönüştürür
        int n = arr.length;
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
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static int[] quickSort(int[] array) {
        // If the array has less than or equal to one element, it is already sorted
        if (array.length <= 1) {
            return array;
        }
        
        // Select the pivot element
        int pivot = array[0];
    
        // Create an array to store the elements that are less than the pivot element
        int[] left = new int[array.length];
        int leftSize = 0;
        for (int x : array) {
            if (x < pivot) {
                left[leftSize] = x;
                leftSize++;
            }
        }
    
        // Create an array to store the elements that are equal to the pivot element
        int[] equal = new int[array.length];
        int equalSize = 0;
        for (int x : array) {
            if (x == pivot) {
                equal[equalSize] = x;
                equalSize++;
            }
        }
    
        // Create an array to store the elements that are greater than the pivot element
        int[] right = new int[array.length];
        int rightSize = 0;
        for (int x : array) {
            if (x > pivot) {
                right[rightSize] = x;
                rightSize++;
            }
        }
    
        // Recursively sort the left, equal, and right arrays
        int[] leftSorted = quickSort(Arrays.copyOfRange(left, 0, leftSize));
        int[] rightSorted = quickSort(Arrays.copyOfRange(right, 0, rightSize));
    
        // Merge the sorted arrays
        int[] sortedArray = new int[array.length];
        System.arraycopy(leftSorted, 0, sortedArray, 0, leftSorted.length);
        System.arraycopy(equal, 0, sortedArray, leftSorted.length, equalSize);
        System.arraycopy(rightSorted, 0, sortedArray, leftSorted.length + equalSize, rightSorted.length);
        return sortedArray;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public static int[] bubbleSort(int[] arr) {
        // Dizinin elemanlarını sırayla karşılaştır
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Eğer ikili sırayla karşılaştırılan elemanlar yerlerini değiştirmek için uygunsa, yer değiştir
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static int[] selectionSort(int[] arr) {
        // Dizinin uzunluğu
        int n = arr.length;
    
        // Her bir eleman için
        for (int i = 0; i < n; i++) {
            // En küçük elemanı bul
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIdx] > arr[j]) {
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