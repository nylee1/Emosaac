from rest_framework import serializers
from recommand.models import ItemBasedcfmodel, UserPredictedGradeModel, Book

class BookSerializer(serializers.ModelSerializer):
    class Meta:
        model = Book
        fields = '__all__'

class ItemBasedcfmodelSerializer(serializers.ModelSerializer):
    class Meta:
        model = ItemBasedcfmodel
        fields = '__all__'

class UserPredictedGradeModelSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserPredictedGradeModel
        fields = '__all__'

# class UserPredictedGradeModelSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = UserPredictedGradeModel
#         fields = '__all__'