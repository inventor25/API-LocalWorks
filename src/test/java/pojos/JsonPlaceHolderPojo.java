package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {


    //İlk önce Encalupse ederek variable'larımızı private olarak oluştur
    private Integer userId;
    private String title;
    private Boolean completed;
/*

        ------- NOTE -------
    Default bir constructor olusturduk parametreli constructor oluşturduğumuzda default olan cons. kaybolacak
    default cons. kaybolduğunda biz de-serialization işlemi yaptığımızda arka planda bu default cons'u kullanacak bu nedenle bu
    default cons olmazsa biz de-serialization işlemi yapamayız
    */


    public JsonPlaceHolderPojo() {

    }

    /*
    Parametreli Constructor ' i kullanarak oluşturduğumuz değişkenlere değer ataması yapabarak obje
    oluşturuyorum
    bu nedenle oluştuşturuldu

     */
    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    /*
    Bu değişkenleri başka Class'lardan okuyabilmemiz için Örnek Response datamızıda bu pojo ya çevirdiğimde
    Do assert yaparken bu class'tan aldığım değişkeni karşılaştırma yapmam gerekecek
    bu nedenle getter 'a ihtiyacımız var değişkenleri okumak için istersek bu dataları constructor'sız
    değiştirebilmemiz için setter ları da oluşturdum
     */
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        //Boolean bir wrapper class olduğundan getCompleted oldu
        //wrapper class olmasaydı isCompleted olacaktı
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    /*

    Değerleri okuyabilmemiz görebilmemiz için toString oluşturdum

     */
    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }


    /*
     {
           "userId": 25,
             "title": "Erzurum da Kayak yapmadan dönme",
             "completed": false,
             "id": 201
            }
     */

}
