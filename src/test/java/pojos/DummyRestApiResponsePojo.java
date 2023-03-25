package pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyRestApiResponsePojo {

    private String status ;
    private DummyRestApiDataPojo data  ;
    private String message  ;

    /*  {
                    "status": "success",
                    "data": {
                        "employee_name": "QA Ahmet",
                        "employee_salary": 2525,
                        "employee_age": 23,
                        "profile_image": "Perfect City" },

                    "message": "Successfully! Record has been updated."
                                                                            }

      */
    public DummyRestApiResponsePojo() {
    }

    public DummyRestApiResponsePojo(String status, DummyRestApiDataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DummyRestApiDataPojo getData() {
        return data;
    }

    public void setData(DummyRestApiDataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyRestApiResponsePojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
