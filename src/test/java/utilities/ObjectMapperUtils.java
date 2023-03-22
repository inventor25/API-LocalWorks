package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtils {

    /*
    generic methodlar kullanılıyor test classında exception istemiyoruz bu yuzden bu util'de  exception ı try
    catch ile handle edecegiz
    <T> T ==> Herhangi bir data tipi
    <T> T ==> Herhangi bir data tipi yani biz burada type kullanmak durumundayız çünkü bu method'un
    ne döneceğini  hangi data tipi return edeceğini bilmiyoruz pojo,object yada hangisiyse bilmiyoruz bu nedenle type kullanıyoruz
    Type  Class<T> cls burada giriline data tipini otomatik olarak algılar ve çevirme işlemini yapar

   **** Eğer public static Object kullanmış olsaydık biz "Class<T> cls" burda girilen datayı tekrar cast yapmak zorunda kalacaktık


    ObjectMapper().readValue(json, cls) methodu birinci parametrede aldığı
     String formatındaki Json datyı ijkinci parametrede belitilen Java objesine çevirir.

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
bu importlar çalışmazsa code house dan yapılması gerekli
     */


    public static <T> T convertJsonToJava(String json, Class<T> cls) { //Generic
        //String'e girilmiş datayı exception atmadan Class data tipine çeviriyoruz
        //Burada girilen json response olarak düşünülebilir
        // tabi stringe çevrilmesi lazım asString kullanılarak()
        // bu method return olarak cls'ye ne girersek onu döner yani json i alır
        // cls'ye çevirip return eder
        //String'e çevrilmiş girilen json'i Object mapper kullanarak cls'ye girilen data tipine exception atmadan
        //çevirdik

        try {
            return new ObjectMapper().readValue(json, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}
