package basics.service.services;

public class AccountServices {
    UserFileRepository userRepository;
    public AccountServices(StorageTarget storageTarget){
        userRepository=storageTarget.getUserFile();
    }
    public boolean callVerifyPassword(String userName,String password){
        try{

        }
    }
}
