import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import static java.lang.System.out;

class Lib {
    static class Book {
        private String id;
        private String title;
        private String author;
        private String year;
        private String genre;
        private String price;
        private String language;
        private String translator;
        private String isbn;
        private String format;
        private Publisher publisher;
        private List<Reviews> reviews = new ArrayList<>();
        private List<String> awards = new ArrayList<>();

        // Геттеры и сеттеры

        public List<Reviews> getReviews() {
            return reviews;
        }

        public void addReview(Reviews review) {
            this.reviews.add(review);
        }

        public void addAward(String award) {
            this.awards.add(award);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getTranslator() {
            return translator;
        }

        public void setTranslator(String translator) {
            this.translator = translator;
        }

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Publisher getPublisher() {
            return publisher;
        }

        public void setPublisher(Publisher publisher) {
            this.publisher = publisher;
        }

        public List<String> getAwards() {
            return awards;
        }

        @Override
        public String toString() {
            return "Book id:" + id + '\n' +
                    '\t' + "Title:" + title + '\n' +
                    '\t' + "Author:" + author + '\n' +
                    '\t' + "Year:" + year + '\n' +
                    '\t' + "Genre:" + genre + '\n' +
                    '\t' + "Price:" + price + '\n' +
                    '\t' + "Language:" + language + '\n' +
                    '\t' + "Translator:" + translator + '\n' +
                    '\t' + "ISBN:" + isbn + '\n' +
                    '\t' + "Format:" + format + '\n' +
                    '\t' + "Publisher:" + (publisher != null ? publisher.toString() : "N/A") + '\n' +
                    '\t' + "Awards:" + awards + '\n' +
                    '\t' + "Reviews:" + reviews + '\n';
        }
    }

    static class Publisher {
        private String name;
        private Address address;

        // Геттеры и сеттеры

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Publisher Name: " + name + ", Address: " + (address != null ? address.toString() : "N/A");
        }
    }

    static class Address {
        private String city;
        private String country;

        // Геттеры и сеттеры

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            return "City: " + city + ", Country: " + country;
        }
    }

    static class Reviews {
        private String user;
        private String rating;
        private String comment;

        // Геттеры и сеттеры

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        @Override
        public String toString() {
            return "user: " + user + '\n' +
                    "rating: " + rating + '\n' +
                    "comment: " + comment + '\n';
        }
    }






    public static String toXml(List<Book> books) {
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        xmlBuilder.append("<library>\n");

        for (Book book : books) {
            xmlBuilder.append("    <book id=\"").append(book.getId()).append("\">\n");
            xmlBuilder.append("        <title>").append(book.getTitle()).append("</title>\n");
            xmlBuilder.append("        <author>").append(book.getAuthor()).append("</author>\n");
            xmlBuilder.append("        <year>").append(book.getYear()).append("</year>\n");
            xmlBuilder.append("        <genre>").append(book.getGenre()).append("</genre>\n");
            xmlBuilder.append("        <price>").append(book.getPrice()).append("</price>\n");
            xmlBuilder.append("        <language>").append(book.getLanguage()).append("</language>\n");
            xmlBuilder.append("        <translator>").append(book.getTranslator()).append("</translator>\n");

            if (book.getIsbn() != null) {
                xmlBuilder.append("        <isbn>").append(book.getIsbn()).append("</isbn>\n");
            }
            if (book.getFormat() != null) {
                xmlBuilder.append("        <format>").append(book.getFormat()).append("</format>\n");
            }
            if (book.getPublisher() != null) {
                xmlBuilder.append("        <publisher>\n");
                xmlBuilder.append("            <name>").append(book.getPublisher().getName()).append("</name>\n");
                if (book.getPublisher().getAddress() != null) {
                    xmlBuilder.append("            <address>\n");
                    xmlBuilder.append("                <city>").append(book.getPublisher().getAddress().getCity()).append("</city>\n");
                    xmlBuilder.append("                <country>").append(book.getPublisher().getAddress().getCountry()).append("</country>\n");
                    xmlBuilder.append("            </address>\n");
                }
                xmlBuilder.append("        </publisher>\n");
            }
            if (!book.getAwards().isEmpty()) {
                xmlBuilder.append("        <awards>\n");
                for (String award : book.getAwards()) {
                    xmlBuilder.append("            <award>").append(award).append("</award>\n");
                }
                xmlBuilder.append("        </awards>\n");
            }
            if (!book.getReviews().isEmpty()) {
                xmlBuilder.append("        <reviews>\n");
                for (Reviews review : book.getReviews()) {
                    xmlBuilder.append("            <review>\n");
                    xmlBuilder.append("                <user>").append(review.getUser()).append("</user>\n");
                    xmlBuilder.append("                <rating>").append(review.getRating()).append("</rating>\n");
                    xmlBuilder.append("                <comment>").append(review.getComment()).append("</comment>\n");
                    xmlBuilder.append("            </review>\n");
                }
                xmlBuilder.append("        </reviews>\n");
            }
            xmlBuilder.append("    </book>\n");
        }

        xmlBuilder.append("</library>");
        return xmlBuilder.toString();
    }
}















public class Main {
    public static String info(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        List<Lib.Book> books = new ArrayList<>();
        Lib.Book book = new Lib.Book();
        Lib.Publisher publisher = null;
        boolean inReview = false;
        boolean inPublisher = false;

        while ((line = reader.readLine()) != null) {
            if (line.contains("<book id=")) {
                book.setId(line.replaceAll("<book id=\"", "").replaceAll("\">", ""));
            } else if (line.contains("<title>")) {
                book.setTitle(line.replaceAll("<title>", "").replaceAll("</title>", ""));
            } else if (line.contains("<author>")) {
                book.setAuthor(line.replaceAll("<author>", "").replaceAll("</author>", ""));
            } else if (line.contains("<year>")) {
                book.setYear(line.replaceAll("<year>", "").replaceAll("</year>", ""));
            } else if (line.contains("<genre>")) {
                book.setGenre(line.replaceAll("<genre>", "").replaceAll("</genre>", ""));
            } else if (line.contains("<price currency=")) {
                book.setPrice(line.replaceAll("<price currency=\"[^\"]*\">", "").replaceAll("</price>", ""));
            } else if (line.contains("<language>")) {
                book.setLanguage(line.replaceAll("<language>", "").replaceAll("</language>", ""));
            } else if (line.contains("<translator>")) {
                book.setTranslator(line.replaceAll("<translator>", "").replaceAll("</translator>", ""));
            } else if (line.contains("<isbn>")) {
                book.setIsbn(line.replaceAll("<isbn>", "").replaceAll("</isbn>", ""));
            } else if (line.contains("<format>")) {
                book.setFormat(line.replaceAll("<format>", "").replaceAll("</format>", ""));
            } else if (line.contains("<publisher>")) {
                inPublisher = true;
                publisher = new Lib.Publisher();
            } else if (inPublisher) {
                if (line.contains("<name>")) {
                    publisher.setName(line.replaceAll("<name>", "").replaceAll("</name>", ""));
                } else if (line.contains("<address>")) {
                    Lib.Address address = new Lib.Address();
                    while (!(line = reader.readLine()).contains("</address>")) {
                        if (line.contains("<city>")) {
                            address.setCity(line.replaceAll("<city>", "").replaceAll("</city>", ""));
                        } else if (line.contains("<country>")) {
                            address.setCountry(line.replaceAll("<country>", "").replaceAll("</country>", ""));
                        }
                    }
                    publisher.setAddress(address);
                } else if (line.contains("</publisher>")) {
                    book.setPublisher(publisher);
                    inPublisher = false;
                }
            } else if (line.contains("<award>")) {
                book.addAward(line.replaceAll("<award>", "").replaceAll("</award>", ""));
            } else if (line.contains("<reviews>")) {
                inReview = true;
            } else if (line.contains("</reviews>")) {
                inReview = false;
            } else if (inReview) {
                if (line.contains("<review>")) {
                    Lib.Reviews review = new Lib.Reviews();
                    while (!(line = reader.readLine()).contains("</review>")) {
                        if (line.contains("<user>")) {
                            review.setUser(line.replaceAll("<user>", "").replaceAll("</user>", ""));
                        } else if (line.contains("<rating>")) {
                            review.setRating(line.replaceAll("<rating>", "").replaceAll("</rating>", ""));
                        } else if (line.contains("<comment>")) {
                            review.setComment(line.replaceAll("<comment>", "").replaceAll("</comment>", ""));
                        }
                    }
                    book.addReview(review);
                }
            } else if (line.contains("</book>")) {
                books.add(book);
                book = new Lib.Book(); // Сброс объекта для следующей книги
            }
        }
        reader.close();

        // Генерация XML из списка книг
        String generatedXml = Lib.toXml(books);
        validateXml(generatedXml);  // Валидация с использованием XSD
        return generatedXml;
    }

    private static void validateXml(String xml) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("library.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new StringReader(xml)));
            out.println("XML is valid.");
        } catch (SAXException e) {
            out.println("XML is not valid: " + e.getMessage());
        } catch (IOException e) {
            out.println("IOException: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        String xmlOutput = info("random_structure_36.xml");
        try (FileWriter writer = new FileWriter("output.xml")) {
            writer.write(xmlOutput);
        }
        out.println(xmlOutput);
    }
}
