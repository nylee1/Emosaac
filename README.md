# D203 PJT2
# EMOSAAC

---

```jsx
모든 작품, 모든 즐거움! 이모작

흩어져서 봐야 하는 웹툰과 웹소설이 불편하지 않으셨나요?
이모작만의 추천을 방법을 통해 
네이버, 카카오, 리디의 웹툰 / 웹소설을 한 곳에서 검색·추천 받으세요.
```

# 프로젝트 소개

---

## 개요

- 삼성청년소프트웨어아카데미(SSAFY) 2학기 특화프로젝트(빅데이터 추천)에서 만든 프로젝트
- 2023.02.27  ~ 2023.04.07 (6주)
- 총 6명 ( 프론트엔드 3명, 백엔드 3명)으로 구성

## 주제

- 네이버, 카카오, 리디의 웹툰 / 웹소설을 한 곳에서 빅데이터 추천방식을 통한 사용자에게 추천해주는 플랫폼

## 아키텍처

![아키텍처](https://user-images.githubusercontent.com/108286046/231908787-2cb17a4a-0756-4294-b908-b05ed8c435eb.png)

## DB

![emosaac-db](https://user-images.githubusercontent.com/108286046/231909843-bab8d386-f82c-44b9-b677-d22556cff534.png)


## 메인페이지

- 로그인 / 웹툰 / 웹소설로 이동할 수 있는 버튼과 이모작의 서비스 소개 페이지
<div align="center">
<img src = "https://github.com/chanho6e1/emosaac/assets/108286046/6b0b7032-fa36-4c50-abbf-3e631ea0bd0f">
</div>

## 로그인

- OAuth2.0을 활용한 네이버 / 카카오 로그인
<div align="center">
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/d090783e-bcb7-43d8-8afd-890223327b52>
</div>

## 설문조사

- 설문조사와 회원정보를 바탕으로 자체 알고리즘 분석으로 추천하기 위한 페이지
- 웹툰/웹소설 각각 최소 5개씩 선정해야만 한다.
<div align="center">
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/a55cb11d-d111-4130-a32c-6c9c44e334b7>
</div>

## 검색기능

- 검색창에서 작품을 검색
- 최근 조회한 목록이 최상단으로 올 수 있도록 구성
- PC 기준 우측에는 인기 태그를 조회하고 클릭하면 바로 태그 검색이 된다.
<div align="center">
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/4b4375cc-574b-48b5-be9a-c1f128b1ebb0>
</div>

## 웹툰 · 웹소설 추천

- 알고리즘 방식을 활용한 개별 사용자에게 작품 추천
- 가로의 경우, 내부적으로는 무한스크롤, 외형상으로는 카로셀의 형태의 View를 적용
- 세로 또한 무한스크롤 View를 사용한다.

        
<div align="center"> 
<img src = "https://github.com/chanho6e1/emosaac/assets/108286046/194bd0c9-85eb-4689-a951-da1fb7fe399f">

</div>

### Item Based Recommendation : 최근 읽은 도서와 유사한 도서 추천
![Item-based](https://user-images.githubusercontent.com/108286046/231909901-611dc7df-f7f3-43da-95f4-9f4ccd15eb46.png)


### User Predicted Grade Recommendation : 사용자 예상 평점 상위 도서 추천
![Predicted-Grade](https://user-images.githubusercontent.com/108286046/231909910-d26ad3aa-a32a-4418-bc9e-049596164d6a.png)


### User-based Collaborative Filtering Recommendation : 유저 베이스 기반 추천 메일 발송

![메일](https://github.com/gms9424/Emosaac/assets/90261873/2c970f76-e166-4120-8daa-ef9def42ef4c)



### 나이, 성별 통계 / 선호 장르 통계

<img src =https://github.com/gms9424/Emosaac/assets/90261873/c20f3553-6c40-483c-9432-2084d1f4e05c>

<img src =https://github.com/gms9424/Emosaac/assets/90261873/d3aed649-c7e1-4234-97f9-5bf2b2b8067a>


## 디테일

- 디테일 페이지는 작품에 대한 전체적인 CRUD를 담당한다.
    - 작품을 조회하고, 댓글을 작성하거나 평점을 메기는 작업, 읽음 처리 등등 추천에 관련된 모든 과정이 디테일 페이지에서 진행된다.
- SSR의 특성인 Search Engine Optimization에 특화된 페이지를 제작하기 위해 로그인이 안되있으면 처음부터 진입이 안되게 만드는 것이 아니라, 진입했을 때 모달폼으로 로그인 창이 뜨도록 만들었다.
    - 이에 따라 여러 검색 엔진 크롤러가 디테일 페이지의 정보에 접근이 용이해졌다.

<div align="center"> 
<img src = https://github.com/chanho6e1/emosaac/assets/108286046/0d62f95e-dfb7-439f-ace7-a475d4084962>
</div>


## 이모픽

- 사용자들이 직접 본인의 추천 리스트를 작성하는 공간
<div align="center"> 
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/e96ac69d-48ec-4a7d-85f3-9b6e7fa77eea>
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/a8e47ca1-6bc9-4da1-b0a3-25b8afef5b08>
</div>

## 마이 페이지

- ApexChart를 통한 사용자의 조회 장르 차트를 통해 사용자의 취향을 알려주는 기능
- 기본적인 개인정보를 보여준다. 좌측 상단의 '수정' 버튼을 클릭하면 개인정보를 수정할 수 있다.
- 가장 많이 조회한 장르, 가장 적게 조회한 장르의 작품을 1~2개씩 추천해주는 기능
- 북마크, 읽음 처리된 작품들을 조회하고, 북마크, 읽음 처리 취소를 하면 바로 목록에서 삭제된다.
<div align="center">
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/24ee94d6-f448-428a-bdbb-3d64a7784495>
</div>

## OCR기능을 이용한 자동 읽음 처리

- Google Cloud VIsion API를 활용한 OCR스캔 기능을 통해 사용자가 첨부한 사진을 스캔하여 작품을 읽음처리
- 읽음처리 이후 평점을 매기게 하여 그를 바탕으로 한 알고리즘 분석으로 사용자에게 작품을 추천
<div align="center"> 
  <img src = "https://github.com/chanho6e1/emosaac/assets/108286046/79bdc49c-17e6-4323-a8fe-b76b181caf14">
  </div>

## 다크모드

- global.css에 css변수의 형태로 라이트모드, 다크모드일 때의 색상을 모두 지정해두고, 해당 변수를 이용하여 컴포넌트 css를 구성하여 다크 모드 토글 한번으로 모든 요소가 다크/라이트 테마에 맞는 css를 가지게 되었다.

<div align="center">
<img src =https://github.com/chanho6e1/emosaac/assets/108286046/0c1d9a6a-f74e-473a-816d-7116c79694e5>
</div>

---

## 배운점 및 아쉬운 점

- 김종혁 : Next.js를 활용한 SSR이 어떤 로직으로 돌아가는지를 몸소 체감할 수 있었던 점이 가장 큰 수확이었던 것 같다. 다만 배울 시간이 부족한 채로 프로젝트에 적용하여 전체적인 일정에 딜레이가 생겼던 점은 아쉽다. 반응형 웹으로 매번 깨지던 레이아웃을 팀원들 덕분에 완벽하게 구현할 수 있게 배운 점 또한 굉장히 만족스러웠다.
- 김동주 : Next.js를 사용하게 되면서 페이지의 내용에 따라 SSR, CSR 중 어떤 방식을 사용할 지 생각하며 구현해 보았다. 또한 이번에는 Infinity Scroll, Carousel, Animated Modal 등을 라이브러리를 사용하지 않고 직접 구현해 볼 수 있는 좋은 기회가 되었다. 마지막으로 팀원들과의 팀워크가 좋아서 매우 만족스러웠다.
- 김현영 :
- 남현지 : 크롤링하여 데이터를 수집하고 데이터를 전처리하여 추천 기능을 구현해볼 수 있는 경험이었다. 의미 있는 추천을 위해 노력할 수 있었다. 배포가 어려웠지만 팀원들의 도움으로 잘 마무리 할 수 있었다. 
- 박찬희 : 직접 수집한 데이터를 활용해 CF 알고리즘을 구현해볼 수 있었다. 그 과정에서 데이터 전처리의 중요성을 배웠다. Next.js 배포를 위해 gitlab-runner와 PM2를 활용했다. 무중단 배포를 하지 못한 점이 아쉽다.
- 이나연 : 데이터 크롤링을 통해 데이터 수집 및 유저 데이터 기반 작품 추천 서비스를 구현했다.
장고와 스프링부트 두 웹 프레임워크를 경험하며 웹 애플리케이션 개발에 대한 이해도를 높일 수 있었다. 또한, 스웨거, 깃, 지라 등을 사용해 협업에 대한 경험도 쌓을 수 있었다.
실시간 크롤링 기능 추가를 못한 아쉬움이 있지만 데이터 수집, 전처리, 분석의 과정을 직접 수행하여 유익한 경험이었다.
