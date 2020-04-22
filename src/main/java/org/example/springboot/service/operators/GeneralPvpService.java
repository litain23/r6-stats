package org.example.springboot.service.operators;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.generalpvp.GeneralPvp;
import org.example.springboot.domain.generalpvp.GeneralPvpRepository;
import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
import org.example.springboot.web.dto.GeneralPvpResponseDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class GeneralPvpService {
    private final GeneralPvpRepository generalPvpRepository;

    public GeneralPvpResponseDto getGeneralPvp(String platform, String id) {
        AuthToken token = UbiAuthApi.getAuthToken();

        API api = new API(token);
        GeneralPvp generalPvp = parseResponseStr(getGeneralPvp(platform, id);
    }

    private GeneralPvp parseResponseStr(String generalPvpStr) {
        Map<String, Double> generalPvpMap = new Gson().fromJson(generalPvpStr, Map.class);



    }

}
