#!/usr/bin/env bash

docker build -t multismith/booking-service:latest .
docker push multismith/booking-service

minikube stop && minikube delete
minikube start --driver=virtualbox
kubectl apply -f workloads.yaml

echo "$(minikube ip)"