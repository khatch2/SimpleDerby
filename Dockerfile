FROM openjdk:17-alpine
LABEL authors="KhatchShah"

ENTRYPOINT ["top", "-b"]