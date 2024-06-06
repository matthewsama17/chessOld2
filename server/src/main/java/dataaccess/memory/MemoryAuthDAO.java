package dataaccess.memory;

import dataaccess.AuthDAO;
import model.AuthData;
import java.util.ArrayList;
import java.util.UUID;

public class MemoryAuthDAO implements AuthDAO {

    private static ArrayList<AuthData> auths = new ArrayList<>();

    @Override
    public String createAuth(String username) {
        String authToken = UUID.randomUUID().toString();
        auths.add(new AuthData(authToken,username));
        return authToken;
    }

    @Override
    public AuthData getAuth(String authToken) {
        for(AuthData auth : auths) {
            if(auth.authToken() == authToken) {
                return auth;
            }
        }
        return null;
    }

    @Override
    public void deleteAuth(String authToken) {
        for(AuthData auth : auths) {
            if(auth.authToken() == authToken) {
                auths.remove(auth);
                break;
            }
        }
    }

    @Override
    public void clear() {
        auths.clear();
    }
}
