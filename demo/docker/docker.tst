./gradlew build
jar 파일을 gradle로 만듬


docker build -t name .

build 이미지를 만들거나
-t 이름을 지정
name 이름
. 경로 이 경우 같은 경로라 .으로 지정

docker run -it -p 5000:5000 --name test jpashop

run 이미지를 실행

-it 컨테이너와 상호작용하는 대화형 모드로 컨테이너 내부의 터미널에 접속 내부에서 명령어를 실행하고 테미널ㅇ 세션을 유지 할 수 있음
-p 5000:5000 포트포워딩

--name 컨테이너 이름은 test

jpashop 이미지 이름