package validators;

import exception.ExceptionConst;
import org.springframework.security.access.AccessDeniedException;
import web.AppUser;

public class OwnResourceValidator {
    public static void validate(AppUser appUser, Long id) {
        if(!appUser.getId().equals(id) && !appUser.getRole().equals("ROLE_ADMIN"))
            throw new AccessDeniedException(ExceptionConst.NOT_ALLOWED);
    }
}
