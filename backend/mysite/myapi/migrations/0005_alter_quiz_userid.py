# Generated by Django 3.2.7 on 2021-11-04 17:32

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('myapi', '0004_quiz'),
    ]

    operations = [
        migrations.AlterField(
            model_name='quiz',
            name='userID',
            field=models.IntegerField(),
        ),
    ]