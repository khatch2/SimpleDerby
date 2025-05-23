FROM alpine/java:latest
LABEL authors="KhatchShah"

ENTRYPOINT ["top", "-b"]