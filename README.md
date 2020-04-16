## 레인보우식스 시즈 전적 검색 사이트
Ubisoft의 api을 이용해서 유저의 전적을 검색해서 보여준다.

### 사용방법

기본적으로 Ubisoft에 로그인을 해야하기 때문에 id와 pw가 필요하다
```$xslt
{
    "email": {email},
    "pw": {pw}
}
```
형식의 ubi-login.json을 만들어 최상위 폴더에 넣어둔다.

---


현재는 UbiSoft에서 가져오는 데이터를 다시 파싱해서 보여주는 API 서버 역할만 구현

|종류|주소|데이터
|----|---|-----|
|RANK|/api/v1/rank/{platform}/{id}?season={season}|랭크 시즌별 MMR, 킬뎃, 승패, 최고MMR 등 보여줌(season 쿼리가 없을 시, 현재 시즌 기준)|
|GENERAL|/api/v1/general/{platform}/{id}|총 킬뎃어시, 플레이타임, 승패 등 기본적인 정보|
|OPERATOR|/api/v1/operator/{platform}/{id}|오퍼레이터 별 킬뎃, 플레이타임, 특수능력 등 정보|



