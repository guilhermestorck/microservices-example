FROM node:6.11-slim

WORKDIR /mocks

ADD catalog-service /mocks/catalog-service
ADD startMocks.sh /mocks/startMocks.sh
RUN chmod +x /mocks/startMocks.sh

EXPOSE 8081 8905

RUN npm install -g stubby
RUN apt-get install -y curl

CMD /bin/bash

ENTRYPOINT ["./startMocks.sh"]
