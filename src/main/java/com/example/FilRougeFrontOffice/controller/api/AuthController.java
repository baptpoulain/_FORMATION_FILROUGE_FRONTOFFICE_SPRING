package com.example.FilRougeFrontOffice.controller.api;


import com.example.FilRougeFrontOffice.controller.dto.SigninRequest;
import com.example.FilRougeFrontOffice.controller.dto.SignupRequest;
import com.example.FilRougeFrontOffice.exception.UserAlreadyExistException;
import com.example.FilRougeFrontOffice.repository.entity.UsersEntity;
import com.example.FilRougeFrontOffice.security.jwt.JwtResponse;
import com.example.FilRougeFrontOffice.security.jwt.JwtUtils;
import com.example.FilRougeFrontOffice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("http://localhost:4200/")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest dto){
        try{
            userService.signup(dto);
            return ResponseEntity.status((HttpStatus.CREATED)).build();
        }catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signgin(@RequestBody SigninRequest dto) throws Exception {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(dto.getUserEmail(), dto.getUserPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(auth);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JSON Web Token (JWT) (RFC 7519)
            String generatedToken = jwtUtils.generateJwt(authentication);


            //TODO : return username from Authentication SecurityContextHolder
            UsersEntity connectedUser = (UsersEntity) authentication.getPrincipal();
            JwtResponse jwtResponse = new JwtResponse(
                    connectedUser.getUserId(),
                    connectedUser.getUserName(),
                    connectedUser.getUserFirstname(),
                    connectedUser.getUserEmail(),
                    connectedUser.getUserPicture(),
                    connectedUser.getIsActive(),
                    connectedUser.getRoleId(),
                    connectedUser.getUserCity(),
                    generatedToken);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jwtResponse);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }





}
