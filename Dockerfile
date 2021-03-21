FROM alpine:3.5

# Install python3 and pip3
RUN apk update
RUN apk add python3
RUN apk add py3-pip

# install Python modules needed by the Python app
COPY requirements.txt /usr/src/app/
# RUN apk add --no-cache \
#         libressl-dev \
#         musl-dev \
#         libffi-dev && \
#     pip3 install --no-cache-dir cryptography==2.1.4 && \
#     apk del \
#         libressl-dev \
#         musl-dev \
#         libffi-dev


RUN pip3 install --upgrade setuptools
RUN pip3 install --upgrade gcloud
RUN pip3 install pyrebase

RUN pip3 install --no-cache-dir -r /usr/src/app/requirements.txt
# RUN pip3 install Flask -q
# RUN pip3 install PyMySQL -q
# RUN pip3 install pdfminer.six -q
# RUN apk add git
# RUN git clone https://github.com/euske/pdfminer.git
# RUN pip3 install pdfminer.six
RUN pip3 install google-cloud-storage -q
RUN pip3 install google-cloud -q
# RUN pip3 install sendgrid -q
RUN pip3 install matplotlib -q
RUN pip3 install numpy -q
RUN pip3 install fpdf -q
# RUN apk add --no-cache tesseract-ocr python3 py3-numpy && \
#     pip3 install --upgrade pip setuptools wheel && \
#     apk add --no-cache --virtual .build-deps gcc g++ zlib-dev make python3-dev py-numpy-dev jpeg-dev && \
#     pip3 install matplotlib && \
#     apk del .build-deps

# copy files required for the app to run
COPY app.py /usr/src/app/
COPY templates /usr/src/app/templates/
COPY static usr/src/app/static/
# tell the port number the container should expose
EXPOSE 5000

# run the application
CMD ["python3", "/usr/src/app/app.py"]
