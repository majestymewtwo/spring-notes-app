package com.springalumni.sairam.service;

import com.google.gson.Gson;
import com.springalumni.sairam.dto.AuthDTO;
import com.springalumni.sairam.dto.LoginDTO;
import com.springalumni.sairam.dto.RegisterDTO;
import com.springalumni.sairam.dto.UserDTO;
import com.springalumni.sairam.exception.ApiException;
import com.springalumni.sairam.mapper.UserMapper;
import com.springalumni.sairam.models.Role;
import com.springalumni.sairam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.springalumni.sairam.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;
    public AuthDTO register(RegisterDTO request) throws Exception {
        if(repository.findByEmail(request.getEmail()).isPresent()){
            throw new ApiException("Email already exits", HttpStatus.UNAUTHORIZED);
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthDTO authenticate(LoginDTO request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthDTO.builder()
                .token(jwtToken)
                .build();
    }
    public AuthDTO verifyOauthToken(String token) throws Exception {
        URL googleVerifyUrl = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + token);
        HttpURLConnection connection = (HttpURLConnection) googleVerifyUrl.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            UserDTO userDTO = new UserDTO();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            reader.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            userDTO.setEmail(jsonObject.getString("email"));
            userDTO.setFirstName(jsonObject.getString("given_name"));
            userDTO.setLastName(jsonObject.getString("family_name"));
            userDTO.setProfilePic(jsonObject.getString("picture"));
            userDTO.setRole(Role.ROLE_STUDENT);
            User user = userMapper.mapToEntity(userService.saveUser(userDTO));
            var jwtToken = jwtService.generateToken(user);
            return AuthDTO.builder()
                    .token(jwtToken)
                    .build();
        }
        return null;
    }
}
