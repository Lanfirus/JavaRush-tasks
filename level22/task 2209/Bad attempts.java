/*
Bad attempts


  public static StringBuilder buildStringSet1(List<String> list, int indexOfStart, int indexOfEnd, boolean singleKey) {

        max = new int[list.size()];
        StringBuilder key1 = new StringBuilder();
        int check = 0;
        String used;

        for (int m = indexOfStart; m < indexOfEnd; m++) {
            List<String> listTmp = new ArrayList<>();
            for (String x : list) {
                listTmp.add(x);
            }
            key1.append(listTmp.get(m));
            listTmp.remove(m);

            for (int i = 0; i < listTmp.size(); i++) {
                StringBuilder previous = new StringBuilder(key1.toString());
                if (key1.charAt(key1.length() - 1) == listTmp.get(i).toLowerCase().charAt(0)) {
                    key1.append(" " + listTmp.get(i));
                } else {
                    continue;
                }
                used = listTmp.get(i);
                listTmp.remove(i);
                check = max[m];
                for (int j = 0; j < listTmp.size(); j++) {
                    if (key1.charAt(key1.length() - 1) == listTmp.get(j).toLowerCase().charAt(0)) {
                        key1.append(" " + listTmp.get(j));
                        listTmp.remove(j);
                        max[m]++;
                        j = -1;
                    }
                }
                if (check == max[m]) {
                    key1 = previous;
                    listTmp.add(used);
                } else {
                    i = -1;
                }
            }
            if (!singleKey) {
                key1 = new StringBuilder();
            }
        }

        return key1;
    }


    public static StringBuilder getLine(String... words) {



        StringBuilder result = new StringBuilder();
        String used;
        int check = 0;

        List<String> list1 = new ArrayList<>();
        for (String x : words) {
            list1.add(x);
            System.out.println(x);
        }


        int max1 = 0;
        StringBuilder key1 = new StringBuilder();


        for (int i = 0; i < list1.size(); i++) {
            StringBuilder previous = new StringBuilder(key1.toString());
            if (key1.length() == 0) {
                key1.append(list1.get(i));
            }
            else if (key1.charAt(key1.length() - 1) == list1.get(i).toLowerCase().charAt(0)) {
                key1.append(" " + list1.get(i));
            }
            else {
                continue;
            }
            used = list1.get(i);
            list1.remove(i);
            check = max1;
            for (int j = 0; j < list1.size(); j++) {
                if (key1.charAt(key1.length() - 1) == list1.get(j).toLowerCase().charAt(0)) {
                    key1.append(" " + list1.get(j));
                    list1.remove(j);
                    max1++;
                    j = -1;
                }
            }
            if (check == max1) {
                key1 = previous;
                list1.add(used);
            }
            else {
                i = -1;
            }
        }

//Another method
        List<String> list2 = new ArrayList<>();
        for (String x : words) {
            list2.add(x);
        }

        int max2 = 0;
        StringBuilder key2 = new StringBuilder();
        for (int i = list2.size() - 1; i >= 0; i--) {
            StringBuilder previous = new StringBuilder(key2.toString());
            if (key2.length() == 0) {
                key2.append(list2.get(i));
            }
            else if (key2.charAt(key2.length() - 1) == list2.get(i).toLowerCase().charAt(0)) {
                key2.append(" " + list2.get(i));
            }
            else {
                continue;
            }
            used = list2.get(i);
            list2.remove(i);
            check = max2;
            for (int j = list2.size() - 1; j >= 0; j--) {
                if (key2.charAt(key2.length() - 1) == list2.get(j).toLowerCase().charAt(0)) {
                    key2.append(" " + list2.get(j));
                    list2.remove(j);
                    max2++;
                    j = list2.size();
                }
            }
            if (check == max2) {
                key2 = previous;
                list2.add(used);
            }
            else {
                i = list2.size();
            }
        }



        //One more another method

        list1.removeAll(list1);
        for (String x : words) {
            list1.add(x);
        }

        int max3 = 0;
        StringBuilder key3 = new StringBuilder();
        for (int i = list1.size() - 1; i >= 0; i--) {
            StringBuilder previous = new StringBuilder(key3.toString());
            if (key3.length() == 0) {
                key3.append(list1.get(i));
            }
            else if (key3.charAt(key3.length() - 1) == list1.get(i).toLowerCase().charAt(0)) {
                key3.append(" " + list1.get(i));
            }
            else {
                continue;
            }
            used = list1.get(i);
            list1.remove(i);
            check = max3;
            for (int j = 0; j < list1.size(); j++) {
                if (key3.charAt(key3.length() - 1) == list1.get(j).toLowerCase().charAt(0)) {
                    key3.append(" " + list1.get(j));
                    list1.remove(j);
                    max3++;
                    j = -1;
                }
            }
            if (check == max3) {
                key3 = previous;
                list1.add(used);
            }
            else {
                i = list1.size();
            }
        }


        //Last method
        list1.removeAll(list1);
        for (String x : words) {
            list1.add(x);
        }

        int max4 = 0;
        StringBuilder key4 = new StringBuilder();
        for (int i = 0; i < list1.size(); i++) {
            StringBuilder previous = new StringBuilder(key4.toString());
            if (key4.length() == 0) {
                key4.append(list1.get(i));
            }
            else if (key4.charAt(key4.length() - 1) == list1.get(i).toLowerCase().charAt(0)) {
                key4.append(" " + list1.get(i));
            }
            else {
                continue;
            }
            used = list1.get(i);
            list1.remove(i);
            check = max4;
            for (int j = list1.size() - 1; j >= 0; j--) {
                if (key4.charAt(key4.length() - 1) == list1.get(j).toLowerCase().charAt(0)) {
                    key4.append(" " + list1.get(j));
                    list1.remove(j);
                    max4++;
                    j = list1.size();
                }
            }
            if (check == max4) {
                key4 = previous;
                list1.add(used);
            }
            else {
                i = -1;
            }
        }


        int[] maxi = new int[4];
        maxi[0] = max1;
        maxi[1] = max2;
        maxi[2] = max3;
        maxi[3] = max4;

        Arrays.sort(maxi);

        if (maxi[3] == max1) {
            result = key1;
            whatMethod = 1;
//            System.out.println("Method1");
        }
        else if (maxi[3] == max2) {
            result = key2;
            whatMethod = 2;
//            System.out.println("Method2");
        }
        else if (maxi[3] == max3) {
            result = key3;
            whatMethod = 3;
//            System.out.println("Method3");
        }
        else if (maxi[3] == max4) {
            result = key4;
            whatMethod = 4;
//            System.out.println("Method4");
        }
        return result;
    }
*/
